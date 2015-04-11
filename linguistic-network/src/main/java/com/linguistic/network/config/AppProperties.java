package com.linguistic.network.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class AppProperties {
	private static final Properties props = new Properties();
	static {
		try {
			InputStream fis = AppProperties.class.getResourceAsStream("/application.properties");
			props.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public static Set<String> getAllPropertyNames() {
		return props.stringPropertyNames();
	}

	public static boolean containsKey(String key) {
		return props.containsKey(key);
	}
}
