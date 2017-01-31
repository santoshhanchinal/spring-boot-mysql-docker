package com.bham.sso.utils;

import java.util.Iterator;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * @author RuJia
 *
 */
public class SqlXssUtil {

	  private static Logger logger=Logger.getLogger(SqlXssUtil.class);
	  private final static String[] SECURITY_ARR={
		  "'","AND ","EXEC","INSERT","SELECT", "DELETE","UPDATE","COUNT","CHR",
		  "MID","MASTER","TRUNCATE","CHAR","DECLARE", "||","--",";", "*",".=",
		  "SCRIPT>","<",">","~", "#","$","^", "&","*","(",")",
		  "SHA","BENCHMARK", "WINDOWS","INI","|", "PASSWD","ETC","PASSWORD",
		  "HERF=","SRC=","ALERT(",".HTML","<SCRIPT",
		  "JAVASCRIPT:","COOKIE","DOCUMENT","IFRAME","EVAL(",
		  "EVAL%28","<IMG","DELAY", "ALERT","XSS","CONFIRM","PROMPT"
	  };
	 
	  //"@",,"%", "!" 

	/*
	 * "'","AND ","EXEC","INSERT","SELECT", "DELETE","UPDATE","COUNT","%","CHR",
	 * "MID","MASTER","TRUNCATE","CHAR","DECLARE", "||","--",";",
	 * "*",".=","%28","%2D","/R/N", "/XSS","SCRIPT>","<",">","~",
	 * "@","#","$","^", "&","*","(",")",";", "SHA","BENCHMARK",
	 * "'","WINDOWS","INI","||","|", "PASSWD","ETC","PASSWORD",
	 * "HERF=","SRC=","ALERT(",".HTML","<SCRIPT",
	 * "JAVASCRIPT:","COOKIE","DOCUMENT","IFRAME","EVAL(",
	 * "EVAL%28","<IMG","\\","DELAY", "ALERT","XSS","CONFIRM","/XSS","PROMPT",
	 * "!","+","/B","/R","/N","/A","/F","/T","/V","/U","/X","/0",
	 * ":","/","HTTP","//","OR","PORT","-","="
	 */
	  
	/**
	 * Whether there is cross-site request forgery (to solve the system outside the chain of requests) return true, there is no return Cross-site request forgery
	 *	Cross - site request forgery
	 * 
	 * @return
	 */
	private static boolean validateCSRFAttack(HttpServletRequest request) {
		String refer = request.getHeader("REFERER"); // Get the address before the request
		String url = request.getRequestURL() + "";

		logger.info("当前请求URL" + request.getRequestURL() + " : 前缀" + refer);
		// If the request address is not within the domain name, the request is considered to be initiated from another page, that there is a risk of forgery
		if (url.contains(".action")) {
			if (refer == null || !refer.contains("gap")) {
/*				logger.info("Initiated request address does not match the originating source address, the system may have suffered a cross-site request forgery");
*/				return true;
			}
		}
		return false;
	}

	/**
	 * The main user filtering URL parameters, get the form of filtering no-persistent xss and persistent xss Dom-based xss
     * (Html-based rules to join the combination of XSS) return true, there is no return false
     *
	 * 
	 * @return
	 */
	private static boolean validateGetData(HttpServletRequest request) {

		String url = request.getRequestURL().toString().toUpperCase();
		if (url == null)
			return false;

		String[] xss_stra = SECURITY_ARR;
		for (int i = 0; i < xss_stra.length; i++) {
			String xssStr = xss_stra[i];
			if (url.toUpperCase().contains(xssStr)) {
				if (url.lastIndexOf("HTTP") == 0) { //Contains http for the first one and continues the loop
					continue;
				}
				/*logger.info("System request URL contains special characters, the special characters:[" + xssStr + "],The current requested link"
						+ "[" + url + "]");*/
				return true;
			}
		}
		return false;
	}

	/**
	 * check Cookie
	 * 
	 */
	public static boolean validateCookieData(HttpServletRequest request) {
		// Filter the values in cookies
		String[] cookie_xss_stra = SECURITY_ARR;
		Cookie[] cookie = request.getCookies();
		if (cookie != null && cookie.length > 0) {
			for (Cookie ck : cookie) {
				String value = ck.getValue();
				if (value != null && !"".equals(value)) {
					// XSS injection
					String temp = value.toUpperCase();
					for (int i = 0; i < cookie_xss_stra.length; i++) {
						String xssStr = cookie_xss_stra[i];
						if (temp.indexOf(xssStr) >= 0) {
							/*logger.info("Cookie injection: xss str[" + xssStr
									+ "],input_str=" + temp);
							logger.info("There is a situation in which the cookie is forged in the current cookie request, where the forged type is:["
									+ xssStr + "],Forged cookie key-value pairs" + "[KEY:"
									+ ck.getName() + ",VALUE:" + value + "]");*/
							return true;
						}
					}
				}
			}
		}
		return false;
	} 

	/**
	 * Verify the values passed by post and verify that the data has XSS and SQL injection
	 * 
	 * @return flag
	 */
	public static boolean validataInputData(HttpServletRequest request) {

		// Filter the value from the post
		String[] inj_stra = SECURITY_ARR;
		Iterator values = request.getParameterMap().values().iterator();// Gets all the form parameters, including data submitted by POST
		while (values.hasNext()) {
			String[] valueArr = (String[]) values.next();
			for (int i = 0; i < valueArr.length; i++) {
				String value = valueArr[i];
				if (value != null) {
					String temp = value.toUpperCase();
					// SQL注入
					for (int j = 0; j < inj_stra.length; j++) {
						String injStr = inj_stra[j];
						if (temp.indexOf(injStr) >= 0) {
							/*logger.info("The value of the post protocol used by the current submission, there is a risk due to contain:[" + injStr
									+ "],The current post value is" + "[" + value + "]");*/
							return true;
						}
					}

				}
			}
		}
		return false;
	}

	/**
	 * Verify that injection is true if the link is injected
	 * 
	 * @param request
	 * @param response
	 * @return flag
	 */
	public static boolean validataRequestHasAttcak(ServletRequest request,
			ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		// Verify the value of the cookie
		// Verify the value that GET gets
		// Verify the value that POST gets
		return validateCookieData(req) || validateGetData(req)
				|| validataInputData(req);
	}


}
