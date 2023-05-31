package com.ohmyzip.test;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.ohmyzip.service.AES256;
public class AES256Test {
		public static void main(String[] args)
		throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException,
		InvalidParameterSpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException,
		InvalidAlgorithmParameterException { 
			String plainText = "1324";
			String key = "%03x";	
			/*
			//단순 암호화
			System.out.println("MD5 : "+ plainText + ", 암호화 : "+AES256.md5(plainText));
			System.out.println("sha256 : "+ plainText + ", 암호화 : "+AES256.sha256(plainText));
			//암호화
			String encText = AES256.encryptAES256(plainText, key);
			System.out.println("AES256 : "+ plainText + ", 암호화 : "+encText);
			//복호화
			String desText = AES256.decryptAES256(encText, key);
			System.out.println("AES256 : "+ encText + ", 복호화 : "+desText);
			*/		
			String[] strArr = {"1324","4321","1111","1234","2222","7979"};
			for(String str : strArr){
				String eText = AES256.encryptAES256(str, key);
				System.out.println("AES256 : "+str+", 암호화 : "+eText);
			}
		}
	}
