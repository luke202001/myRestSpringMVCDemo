package cn.edu.tju.rico.authorization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



import cn.edu.tju.rico.utils.CodecUtil;
import cn.edu.tju.rico.utils.StringUtil;
  
/**        
 * Title: TokenManager��Ĭ��ʵ��    
 * Description: ���� Token
 * @author rico       
 * @created 2017��7��4�� ����4:41:32    
 */      
public class DefaultTokenManager implements TokenManager {

	/** ��token�洢��JVM�ڴ�(ConcurrentHashMap)��   (@author: rico) */      
	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	/** 
	 * @description ����UUID����Token(�û���¼ʱ������Token)
	 * @author rico       
	 * @created 2017��7��4�� ����4:46:46      
	 * @param username
	 * @return     
	 * @see cn.edu.tju.rico.authorization.TokenManager#createToken(java.lang.String)     
	 */  
	public String createToken(String username) {
		String token = CodecUtil.createUUID();
		tokenMap.put(token, username);
		return token;
	}

	  
	/** 
	 * @description Token��֤(�û���¼��֤)
	 * @author rico       
	 * @created 2017��7��4�� ����4:46:50      
	 * @param token
	 * @return     
	 * @see cn.edu.tju.rico.authorization.TokenManager#checkToken(java.lang.String)     
	 */  
	public boolean checkToken(String token) {
		return !StringUtil.isEmpty(token) && tokenMap.containsKey(token);
	}

	  
	/** 
	 * @description Tokenɾ��(�û��ǳ�ʱ��ɾ��Token)
	 * @author rico       
	 * @created 2017��7��4�� ����4:46:54      
	 * @param token     
	 * @see cn.edu.tju.rico.authorization.TokenManager#deleteToken(java.lang.String)     
	 */
	//@Override
	public void deleteToken(String token) {
		// TODO Auto-generated method stub
		tokenMap.remove(token);
	}
}
