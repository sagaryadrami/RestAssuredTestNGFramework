package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	public static RequestSpecification getRequestSpec() {
		
		return new RequestSpecBuilder()
				//.setBaseUri(System.getProperty("BASE_URI"))
//in order to use System.getProperty the in terminal run this cmd >mvn test -DBASE_URI="https://api.spotify.com" -DAccounts_BASE_URI="https://accounts.spotify.com"
		.setBaseUri("https://api.spotify.com")
		.setBasePath(Endpoints.BASE_Path)
		.setContentType(ContentType.JSON)
		.addFilter(new AllureRestAssured())
		.log(LogDetail.ALL)
		.build();	
	}
	
	public static RequestSpecification getAccountRequestSpec() {
		return new RequestSpecBuilder()
				//.setBaseUri(System.getProperty("Accounts_BASE_URI"))
//in order to use System.getProperty the in terminal run this cmd >mvn test -DBASE_URI="https://api.spotify.com" -DAccounts_BASE_URI="https://accounts.spotify.com"
	    .setBaseUri("https://accounts.spotify.com")
		.setContentType(ContentType.URLENC)
		.addFilter(new AllureRestAssured())
		.log(LogDetail.ALL)
		.build();	
	}
	
	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder()
		.log(LogDetail.ALL)
		.build();
	}
}
