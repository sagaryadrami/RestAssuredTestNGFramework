package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {

	
	public static Response post(String path,String token,Object requestPlaylist) {
		
		return given(SpecBuilder.getRequestSpec())
				.body(requestPlaylist)
				.auth().oauth2(token)
			.when()
				.post(path)
			.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
	
	public static Response postAccounts(HashMap<String,String> formparam) {
		return given(SpecBuilder.getAccountRequestSpec())
				.formParams(formparam)
				.log().all()
				.when().post(Endpoints.API+ Endpoints.TOKEN)
				.then().spec(SpecBuilder.getResponseSpec())
				.extract().response();
	}
		
	public static Response get(String path,String token) {
		
		return given(SpecBuilder.getRequestSpec())
				.auth().oauth2(token)
				.when().get(path)
				.then().spec(SpecBuilder.getResponseSpec())
				.assertThat()
				.statusCode(200)
				.extract().response();
	}
	
	public static Response put(String token,Object requestPlaylist,String path) {
		
	 return	given(SpecBuilder.getRequestSpec())
		.body(requestPlaylist)
		.auth().oauth2(token)
	.when().put(path)
	.then().spec(SpecBuilder.getResponseSpec())
	.extract().response();
		
	}
}
