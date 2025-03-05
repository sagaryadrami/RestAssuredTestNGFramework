package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

	private final Properties properties;
	private static ConfigLoader configLoader;
	
	  private ConfigLoader() {
	        properties = PropertyUtils.propertyLoader("C:/Users/syadrami/Documents/practise eclipse/RestAssuredFramework/src/test/java/resources/config.property");
	        if (properties.isEmpty()) {
	            throw new RuntimeException("⚠️ Properties file loaded but is EMPTY! Check the path or file content.");
	        }
	        System.out.println("✅ Loaded Properties: " + properties); // Debugging
	    }

	
	public static ConfigLoader getInstances() {
		if(configLoader==null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}
	
	
	
	
	public String getClientId() {
		String prop=properties.getProperty("client_id");
		if(prop != null) return prop;
		else throw new RuntimeException("property client_id is not specifies in the config.property file");
	}
	
	public String getclient_secret() {
		String prop=properties.getProperty("client_secret");
		if(prop != null) return prop;
		else throw new RuntimeException("property client_secret is not specifies in the config.property file");
	}
	
	public String getrefresh_token() {
		String prop=properties.getProperty("refresh_token");
		if(prop != null) return prop;
		else throw new RuntimeException("property refresh_token is not specifies in the config.property file");
	}
	
	public String getgrant_type() {
		String prop=properties.getProperty("grant_type");
		if(prop != null) return prop;
		else throw new RuntimeException("property grant_type is not specifies in the config.property file");
	}
	
	public String getuser() {
		String prop=properties.getProperty("user_id");
		if(prop != null) return prop;
		else throw new RuntimeException("property user_id is not specifies in the config.property file");
	}
	
	
}
