package cn.edu.tju.rico.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

  
/**        
 * Title: Web�����Ĺ�����
 * @author rico       
 * @created 2017��7��4�� ����5:16:42    
 */      
public class WebContextUtil {
	  
	/**     
	 * @description ��ȡHTTP����    
	 * @author rico       
	 * @created 2017��7��4�� ����5:18:08     
	 * @return     
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}
}
