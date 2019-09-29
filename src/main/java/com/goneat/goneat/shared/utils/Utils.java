package com.goneat.goneat.shared.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	Random random = new SecureRandom();
	private final String RANDOMSTRING= "abcdefghijklmnopqrstuvwxyz012345678ABCDEFGHIJKLMNOPQRSTUVWXYZ9";
	
	public String getUserId(int length)
	{
		return generateRandomId(length);
	}
	
	private String generateRandomId(int length)
	{
		StringBuilder str=new StringBuilder();
		for(int i=0;i<length;i++)
		{
			str.append(RANDOMSTRING.charAt(random.nextInt(RANDOMSTRING.length())));
		}
		return str.toString();
	}
}
