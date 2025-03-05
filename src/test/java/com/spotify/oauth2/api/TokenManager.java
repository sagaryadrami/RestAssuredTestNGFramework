package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	//checking if the token is expireed or not 
	public synchronized static String getToken() {
		
		try{
			
			if(access_token==null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing the token ");
				Response response = renewToken();
				access_token = response.path("access_token");
				int expiresInSecond = response.path("expires_in");
				expiry_time = Instant.now().plusSeconds(expiresInSecond);
				
			}else {
				System.out.println("Token is goot to use");
			}
			
		}catch(Exception e) {
			throw new RuntimeException("Abort !! failed to get the token");
		}
		
		return access_token;
	}
	
	private static Response renewToken() {
	HashMap<String ,String> formparam = new HashMap<String,String>();
	formparam.put("client_id", ConfigLoader.getInstances().getClientId());
	formparam.put("client_secret", ConfigLoader.getInstances().getclient_secret());
	formparam.put("refresh_token", ConfigLoader.getInstances().getrefresh_token());
	formparam.put("grant_type", ConfigLoader.getInstances().getgrant_type());
	
	
	Response response = RestResource.postAccounts(formparam);
	
	if(response.statusCode() != 200) {
		throw new RuntimeException("Abort!!! Renew Token Failed");
	}
	return response;
	}
}
