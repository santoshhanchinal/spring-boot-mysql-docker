package com.bham.sso.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

/**
 * Public utility class
 * @Description: Gets the information about the client
 * @author Federico Bacci
 * @author Rujia Li 
 * @author Alessandro Pozzer
 * @date   06/10/2016
 * 
 * reference:
 * http://leiyongping88.iteye.com/blog/1545930
 *
 */
public class ClientInfoUtil {
	
	/**
	 * Obtain the client IP address, taking into account the situation with the agent
	 * @return
	 */
	public static String getClientIpAddr(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
				
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress= inet.getHostAddress();
			}
		}
		//For multiple agents, the first IP is the client's real IP, and the multiple IPs are separated by ','
		if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
			if(ipAddress.indexOf(",")>0){
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	/**
	 * Gets the version of the operating system
	 * @return
	 */
	public static String getClientOS(HttpServletRequest request){
		String Agent = request.getHeader("User-Agent");
		StringTokenizer st = new StringTokenizer(Agent,";");
		st.nextToken();
		st.nextToken();
		return  st.nextToken();
	}

	/**
	 * Gets the version of the browser system
	 * @return
	 */
	public static String getClientBrowser(HttpServletRequest request){
		String Agent = request.getHeader("User-Agent");
		if(Agent==null) return null;
		StringTokenizer st = new StringTokenizer(Agent,";");
		if(st==null) return null;
		st.nextToken();
		return  st.nextToken();
	}
	
	/**
	 * Gets the full path to the originating request
	 * @param request
	 * @return
	 */
	public static String getClientFullURL(HttpServletRequest request) {
		 StringBuffer url = request.getRequestURL();
		 if (request.getQueryString() != null) {
		  url.append('?');
		  url.append(request.getQueryString());
		 }
		 return url.toString();
	}
	
    /**
     * Get the MAC address
     * @param ip
     * @return
     */
    public static String getMACAddress(String ip) {  
        String str = "";  
        String macAddress = "";  
        try {  
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
            InputStreamReader ir = new InputStreamReader(p.getInputStream());  
            LineNumberReader input = new LineNumberReader(ir);  
            for (int i = 1; i < 100; i++) {  
                str = input.readLine();  
                if (str != null) {  
                    if (str.indexOf("MAC Address") > 1) {  
                        macAddress = str.substring(  
                                str.indexOf("MAC Address") + 14, str.length());  
                        break;  
                    }  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace(System.out);  
        }  
        return macAddress;  
    } 
	
}
