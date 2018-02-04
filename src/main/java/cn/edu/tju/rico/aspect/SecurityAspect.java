package cn.edu.tju.rico.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import cn.edu.tju.rico.annotation.IgnoreSecurity;
import cn.edu.tju.rico.authorization.TokenManager;
import cn.edu.tju.rico.exception.TokenException;
import cn.edu.tju.rico.utils.Constants;
import cn.edu.tju.rico.utils.WebContextUtil;

/**
 * Title:��ȫ�������(�Ƿ��¼���) 
 * Description: ͨ����֤Tokenά�ֵ�¼״̬
 * 
 * @author rico
 * @created 2017��7��4�� ����4:32:34
 */
@Component
@Aspect
public class SecurityAspect {

	/** Log4j��־����(@author: rico) */
	private static final Logger log = Logger.getLogger(SecurityAspect.class);

	private TokenManager tokenManager;

	@Resource(name = "tokenManager")
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		// ���е��ϻ�ȡĿ�귽��
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		log.debug("methodSignature : " + methodSignature);
		Method method = methodSignature.getMethod();
		log.debug("Method : " + method.getName() + " : "
				+ method.isAnnotationPresent(IgnoreSecurity.class));
		// ��Ŀ�귽�������˰�ȫ�Լ��,��ֱ�ӵ���Ŀ�귽��
		if (method.isAnnotationPresent(IgnoreSecurity.class)) {
			return pjp.proceed();
		}

		// �� request header �л�ȡ��ǰ token
		String token = WebContextUtil.getRequest().getHeader(
				Constants.DEFAULT_TOKEN_NAME);
		// ��� token ��Ч��
		if (!tokenManager.checkToken(token)) {
			String message = String.format("token [%s] is invalid", token);
			log.debug("message : " + message);
			throw new TokenException(message);
		}
		// ����Ŀ�귽��
		return pjp.proceed();
	}
}
