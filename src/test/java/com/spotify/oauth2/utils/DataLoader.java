package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {

	private final Properties properties;
	private static DataLoader dataLoader;
	
	  private DataLoader() {
	        properties = PropertyUtils.propertyLoader("C:/Users/syadrami/Documents/practise eclipse/RestAssuredFramework/src/test/java/resources/data.properties");
	        if (properties.isEmpty()) {
	            throw new RuntimeException("⚠️ Properties file loaded but is EMPTY! Check the path or file content.");
	        }
	        System.out.println("✅ Loaded Properties: " + properties); // Debugging
	    }

	
	public static DataLoader getInstances() {
		if(dataLoader==null) {
			dataLoader = new DataLoader();
		}
		return dataLoader;
	}
	
	
	
	
	public String getGetPlaylistId() {
		String prop=properties.getProperty("get_playlist_id");
		if(prop != null) return prop;
		else throw new RuntimeException("property client_id is not specifies in the config.property file");
	}
	
	public String getUpdatePlaylistId() {
		String prop=properties.getProperty("update_playlist_id");
		if(prop != null) return prop;
		else throw new RuntimeException("property client_secret is not specifies in the config.property file");
	}
}
