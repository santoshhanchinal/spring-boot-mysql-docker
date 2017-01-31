package com.bham.sso.utils;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	/**
	 * Cookie operation tool class
	 * 
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 * @author RuJia Li
	 * @author Alessandro Pozzer
	 * @author Federico Bacci
	 */

	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals(cookieName)) {
						return cookie;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCookieValue(HttpServletRequest request,	String cookieName) {
		if (cookieName != null) {
			Cookie cookie = getCookie(request, cookieName);
			if (cookie != null) {
				return cookie.getValue();
			}
		}
		return "";
	}

	// Session cookie, the default Domain for the domain name, the default path for all paths
	public static void setCookie(HttpServletResponse response, String name,
			String value) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath("/");
		cookies.setMaxAge(-1); //Session cookies, stored in the browser's memory, and browsers disabled for conversation
		//cookies.setSecure(true); //Cookie is set to HttpOnly is to prevent XSS attacks, stealing cookie content
		response.addCookie(cookies);
	}

	// Session cookie, the default path for all paths, specify the domain shared cookie settings gap.com.cn to meet all * .gap.com.cn domain name sharing
	// Cookie and common information for single sign-on use
	public static void setCookie(HttpServletResponse response, String name,
			String value, String domain) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath("/");
		cookies.setMaxAge(-1); // Session cookies, stored in the browser's memory, and browsers disabled for conversation
		cookies.setDomain(domain);
		//cookies.setSecure(true); //Cookie is set to HttpOnly is to prevent XSS attacks, stealing cookie content
		response.addCookie(cookies);
	}
	
	/**
	 * Add cookies in bulk
	 * @param response
	 * @param map
	 * @param domain
	 */
	public static void setCookie(HttpServletResponse response, Map<String,String> map, String domain) {
		if(map!=null&&map.size()!=0){
			for (Object key : map.keySet()) {
				   if(key!=null){
					   String name = key+"";
					   String value = map.get(key) +"";
					   setCookie(response,name,value,domain);
				   }
		    }
		}
	}

	// Session cookie, the default path for all paths, specify the domain and path
	public static void setCookie(HttpServletResponse response, String name,
			String value, String domain, String path) {
		Cookie cookies = new Cookie(name, value);
		cookies.setPath("/");
		cookies.setMaxAge(-1); // Session cookies, stored in the browser's memory, and browsers disabled for conversation
		cookies.setDomain(domain);
		cookies.setPath(path);
		//cookies.setSecure(true); //Cookie is set to HttpOnly is to prevent XSS attacks, stealing cookie content
		response.addCookie(cookies);
	}

	// Timed cookies, specify the time, the default path for the full path, such as logging the last login user name to use
	public static void setCookie(HttpServletResponse response, String name,
			String value, int age) {
		Cookie cookies = new Cookie(name, value);
		cookies.setMaxAge(age);
		response.addCookie(cookies);
	}

	//timed cookies, specify the time specified path available
	public static void setCookie(HttpServletResponse response, String name,
			String value, int age, String path) {
		Cookie cookies = new Cookie(name, value);
		cookies.setMaxAge(age);
		cookies.setPath(path);
		//cookies.setSecure(true); //Cookie is set to HttpOnly is to prevent XSS attacks, stealing cookie content
		response.addCookie(cookies);
	}

	public static void setCookie(HttpServletResponse response, String name,
			String value, String Domian, int age, String path) {
		Cookie cookies = new Cookie(name, value);
		cookies.setMaxAge(age);
		cookies.setDomain(Domian);
		cookies.setPath(path);
		//cookies.setSecure(true); //Cookie is set to HttpOnly is to prevent XSS attacks, stealing cookie content
		response.addCookie(cookies);
	}

	public static boolean hasCookie(HttpServletRequest request,
			String cookieName) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					cookie = cookies[i];
					if (cookie.getName().equals(cookieName)) return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Delete the cookie
	public static void delCookie(HttpServletResponse response, Cookie delcookie,String domain,String path) {
		Cookie cookie = new Cookie(delcookie.getName(), null);
		if(path!=null && !path.equals("") )  cookie.setPath(path);
		if(domain!=null && !domain.equals("") ) cookie.setDomain(domain);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	// Delete cookies by name, path, and domain (name, path, and domain can uniquely target a cookie)
	public static void delCookie(HttpServletResponse response, String name,
			String path, String domian) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(path);
		cookie.setDomain(domian);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	// Removes all the session cookies that are currently readable
	public static void delAllSessionCookie(HttpServletRequest request,
			HttpServletResponse response,String domain) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getMaxAge() == -1) {
					delCookie(response, cookie,domain,"/");
				}
			}
		}
	}

	// Removes all currently readable cookies from the current request
	public static void delAllCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				delCookie(response, cookies[i],"","/");
			}
		}
	}

}
