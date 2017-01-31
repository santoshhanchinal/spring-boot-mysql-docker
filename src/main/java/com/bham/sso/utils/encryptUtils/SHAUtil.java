package com.bham.sso.utils.encryptUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author http://blog.csdn.net/joyous/article/details/49898383
 *
 */
public class SHAUtil {
	
	/** 
	   * Incoming text, return SHA256 string
	   *  
	   * @param strText 
	   * @return sha256
	   */  
	  public static String SHA256(final String strText)  
	  {  
	    return SHA(strText, "SHA-256");  
	  }  
	  
	  /** 
	   * Returns the text of the SHA512 string
	   *  
	   * @param strText 
	   * @return sha512
	   */  
	  public static String SHA512(final String strText)  
	  {  
	    return SHA(strText, "SHA-512");  
	  }  
	  
	  /** 
	   * String SHA encryption
	   *  
	   * @param strSourceText 
	   * @return 
	   */  
	  private static String SHA(final String strText, final String strType)  
	  {  
	    // return value
	    String strResult = null;  
	  
	    //Is a valid string
	    if (strText != null && strText.length() > 0)  
	    {  
	      try  
	      {  
	        // SHA encryption starts
	        // Create an encrypted object and pass it in the encryption type
	        MessageDigest messageDigest = MessageDigest.getInstance(strType);  
	        // Passes the string to be encrypted
	        messageDigest.update(strText.getBytes());  
	        // Get byte type results
	        byte byteBuffer[] = messageDigest.digest();  
	  
	        // Converts a byte to string
	        StringBuffer strHexString = new StringBuffer();  
	        // Traverses the byte buffer
	        for (int i = 0; i < byteBuffer.length; i++)  
	        {  
	          String hex = Integer.toHexString(0xff & byteBuffer[i]);  
	          if (hex.length() == 1)  
	          {  
	            strHexString.append('0');  
	          }    
	          strHexString.append(hex);  
	        }  
	        // Get the return result
	        strResult = strHexString.toString();  
	      }  
	      catch (NoSuchAlgorithmException e)  
	      {  
	        e.printStackTrace();  
	      }  
	    }  
	  
	    return strResult;  
	  }  
	  

	/**
	 * Test the main function
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = new String("1qaz2wsx");
		System.out.println("befror" + str);
		System.out.println("SHA" + SHA256(str));
	}
}