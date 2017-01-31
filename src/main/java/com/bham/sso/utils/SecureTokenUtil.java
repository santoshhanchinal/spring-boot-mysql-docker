package com.bham.sso.utils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.codec.binary.Base64;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.bham.sso.utils.encryptUtils.AESUtils;
//import com.bham.teamwork.utils.encryptUtils.RSAUtils;
/**
 * 
 * @author RuJiaLi
 *
 */
public class SecureTokenUtil {

	private static final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
    private static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
    private static final int KEY_SIZE = 128;
    private static final int ITERATION_COUNT = 10000;
    private static final String PASSPHRASE = "the quick brown fox jumps over the lazy dog";
	/**
	 * 
	 * @param userId
	 * @param clientIp
	 * @param date
	 * @param timeout
	 * @return
	 * @throws Exception
	 * 
	 */
	public static String creatLtapToken(String userId,String clientIp,Date date,String timeout) throws Exception{
		
		//String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMrnsGelxxzH+4IisZQkzo800L7QzawvtmAkuDccc5+7cvlKZBUl0FpCS5VsZDwKOOU6XYnBCdm+Us605sCcbxzLrXLwTlWOiS3e7zbIMoyNLOeSj1C+0Gso2ohneT2G/ACQGZhz1bC3PTIJ7Fo0Gh2UZhz6Km2ihqmMmqSP+4IFAgMBAAECgYB4x4+PMvQiIryIy2QPPhWi0xqK6znyi/gwpMrvK0V8SJV45RpMKFUs1Reu23WS87bIZIS3ciwN7CfYmYBeGQBeAGQd+umN7rL60V2tZHxHMJJzIqrV07PfCexVQKmUQ56bOQ0GfSbrbAD1QIQ88Ua6eB03XzC72ffWS6PL2720BQJBAPgYhKSeCOqMM/Y0bKPA0XiowU1Hhq2ih01aI1YvFU6mp5G1lVxtGSxO5XF9hKWJkp/zVx/dx/oNd58ZuCcUB3cCQQDRXpfMZayqEmzAt2KpQNZrYhLaJuBbBQqwB98PUMdmN65ED3rsbgCGrr37QdqAX2SompiSDgysncIXOc4aDhljAkBy8wE6OJLPAajtsTqI4MTtT9tIUBShjMV93H605tnLeEH5rBWJHm9kbSW34L096bEK9Tdv51VJkUXUbJk7WfdfAkEAinhVTQmxuImXA55F0krfhQXIEh/EIm6jMukBzc9PEXuh9cGHOvdFwc2wOIzFRkHRAPG+FlrUZxWvWE0S0oigoQJAGzIKxwmaytRJ+Z6kJWec9AUY6QOfHmqhpgYPxrnJMA2P6JMXMntmrkqrerrmbKgwE5GtTG2W8jGGguyQFs19bQ==";
		
		Long startTime = date.getTime()-(-10*60000);  // Get the start time, plus inclusive time, the server time synchronization requirements decreased
		Long endTime   = date.getTime()+(Long.parseLong(timeout)*60000);  // The time format for the timeout is minutes, and * 60000 means to change minutes to milliseconds
		
		StringBuilder sb = new StringBuilder();
		sb.append("0101"); //Check code
		sb.append("|");
		sb.append(startTime/1000);
		sb.append("|");
		sb.append(endTime/1000);
		sb.append("|");
		sb.append(clientIp);
		sb.append("|");
		sb.append(userId);
		
		
		/*byte[] data = sb.toString().getBytes();
		byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
		return new String(Base64.encodeBase64(encodedData)); */
		//String encryptResult = AESUtils.encryptAES(sb.toString(), "****");  
		AESUtils util = new AESUtils(KEY_SIZE, ITERATION_COUNT);
        String encryptResult = util.encrypt(SALT, IV, PASSPHRASE, sb.toString());
		//String encryptResultStr = AESUtils.parseByte2HexStr(encryptResult);
        return encryptResult;
	}
	/**
	 * Resolve the Ltpatoken session string
	 * @Description: TODO
	 * @param @return   
	 * @return LtapTokenInfo  
	 * @throws Exception 
	 * @throws
	 * @author Alessandro Pozzer
	 * @author Federico Bacci
	 * @author Rujia Li
	 * @date 2015-7-14 下午12:30:19
	 */
	public static String analyzeLtapToken(String ltapToken) throws Exception{
		//String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDK57Bnpcccx/uCIrGUJM6PNNC+0M2sL7ZgJLg3HHOfu3L5SmQVJdBaQkuVbGQ8CjjlOl2JwQnZvlLOtObAnG8cy61y8E5Vjokt3u82yDKMjSznko9QvtBrKNqIZ3k9hvwAkBmYc9Wwtz0yCexaNBodlGYc+iptooapjJqkj/uCBQIDAQAB";
		//byte[] encodedData = Base64.decodeBase64(ltapToken.getBytes()); 
		//byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
		//String outputStr = new String(decodedData);
		//return outputStr;
	    //byte[] decryptFrom = AESUtils.parseHexStr2Byte(ltapToken);  
	    //String decryptResult = AESUtils.decryptAES(ltapToken, "****");  
	    AESUtils util = new AESUtils(KEY_SIZE, ITERATION_COUNT);
        String decryptResult = util.decrypt(SALT, IV, PASSPHRASE, ltapToken);
	    return decryptResult;
	}
	
	
	
	public static byte[] createHash(byte[] workingBuffer) {
		// TODO Auto-generated method stub
		 // Performs a SHA1 checksum
	    MessageDigest md; 
	    try { 
	        md = MessageDigest.getInstance("SHA-1"); 
	        md.reset(); 
	    } catch (NoSuchAlgorithmException e) { 
	        throw new RuntimeException(e); 
	    } 
	    byte[] digest = md.digest(workingBuffer); 
	    //System.out.println(digest.length);
		return digest;
	} 
	
    /**
     * Determine whether the exception is a link
     * @param reqeustURI
     * @param exceptionUrls
     * @return
     */
	public static boolean isExceptionURI(String reqeustURI,String[] exceptionUrls){
        PathMatcher matcher = new AntPathMatcher(); 
        for(String url:exceptionUrls){
        	if(matcher.match(url, reqeustURI))
        		return true;
        }
    	return false;
    }
	
	
	
	public static String getRequestUrl(HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
	    if (request.isSecure())
	        sb.append("https://");
	      else
	        sb.append("http://");
	    sb.append(request.getHeader("host"));
	    sb.append(request.getRequestURI());
	    if (request.getQueryString() != null)  sb.append("?" + request.getQueryString());
	    return URLEncoder.encode(sb.toString());
	}
	
	
	public static void setRedirectToCookie(HttpServletRequest request,HttpServletResponse response){
		StringBuffer sb = new StringBuffer();
	    if (request.isSecure())
	        sb.append("https://");
	      else
	        sb.append("http://");
	    sb.append(request.getHeader("host"));
	    sb.append(request.getRequestURI());
	    if (request.getQueryString() != null)  sb.append("?" + request.getQueryString());
	    CookieUtil.setCookie(response, "redirectToUrl", URLEncoder.encode(sb.toString()), "");
	}
	
	
	/**
	 * 测试
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
	/*	Long startTime = new Date().getTime();
		String s = creatLtapToken("xhao", "30","10.142.51.66");
		Long endTime = new Date().getTime();
		System.out.println(endTime-startTime);
		System.out.println(s);
		Long startTime1 = new Date().getTime();
		String re = analyzeLtapToken(s);
		
		Long endTime1 = new Date().getTime();
		System.out.println(endTime1-startTime1);*/
		
		/*String strUser = "<uri>60000000@gd.cp</uri><nam>mengxh</nam>";
		String requestUserId  = strUser.substring(strUser.indexOf("<uri>")+5, strUser.indexOf("</uri>"));
		System.out.println(requestUserId);*/
		
		String[] s = new String[]{"/**/*.action","/user/s/d"};
		
		//   /**/user/**
		//   /**/*.action
		//   /**/user_*.action
		//   /**/xmlViewToPortal.action
		
		String url = "/ll/user/s/d.action";
		System.out.println(isExceptionURI(url,s));
		
		
	}


}
