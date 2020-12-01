package com.revature.amazon.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public MD5Util() {
        super();
        
	}

	public static String getHashedCode(String valueToHash) {
		if (valueToHash != "") {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");

				md.update(valueToHash.getBytes());

				byte[] bytes = md.digest();

                StringBuilder sb = new StringBuilder();
                
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                
                }

				return sb.toString();
			} catch (NoSuchAlgorithmException e) {
				
			}
		}
        return null;
        
	}
}