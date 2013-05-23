package com.kong.shop.utils;

import java.io.IOException;
import java.util.Properties;

public class SiteUrl{
	
	private static Properties p = new Properties();
	
	static {
		try {
			p.load(SiteUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String read(String key) {
		return p.get(key).toString();
	}

}
