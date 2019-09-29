package com.goneat.goneat.security;

import com.goneat.goneat.SpringApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SecurityConstants {

	public static final String TOKEN_PREFIX="Bearer";
	public static final String HEADER_STRING="Authorization";
	public static final String SIGN_UP_URL="/user";
	public static final String TOKEN_SECRET = "ksjfh8uuhfu374y";

	public static String getTokenSecret()
	{
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}
	public static String getExpirationTime()
	{
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getExpirationTime();
	}


}
