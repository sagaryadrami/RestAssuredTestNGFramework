package com.spotify.oauth2.applicationAPI;

import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.Endpoints;
import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlaylistApi {
	

	//static String access_token ="BQDxJ-9X27WhX9A3aWZP8EtjExm6yWBpLTLyLvUCnqxanR3ufDLQ9JP0v5MrZgBtsA0BAEp6cbdk4tKtPnDyJOce4e1845Cz_ZU6jFrzajc0_NbGNa4IN7y0ge2FB-fqs-xoLmn5yH37-hAAa7hZPtzKPxHtvssW91FFBQSBWbZpFywJmZduHrXuTOw9P8vDBgRjSwfVb3lxD1_STzOsylZ8wSSL7IaqWj1FbXS02D9jyqsBhzOq9rTsFvjz89C7cJBj540PJ1fA8u391U0P3FqVaScB9htrJhuPYbWlVcU7gyWI";
			
	@Step	
	public static Response post(Playlist requestPlaylist) {
		return RestResource.post(Endpoints.USERS +"/" +ConfigLoader.getInstances().getuser() 
				+ Endpoints.PLAYLIST, TokenManager.getToken(), requestPlaylist);
	}
	
	@Step
	public static Response post(Playlist requestPlaylist,String token) {
		return RestResource.post(Endpoints.USERS+"/" +ConfigLoader.getInstances().getuser()
				+Endpoints.PLAYLIST, token, requestPlaylist);
	}
	
	public static Response get(String PlaylistId ) {
		return RestResource.get(Endpoints.PLAYLIST + "/" + PlaylistId, TokenManager.getToken());
	}
	
	@Step
	public static Response put(Playlist requestPlaylist,String PlaylistId) {
		return RestResource.put( TokenManager.getToken(), requestPlaylist,Endpoints.PLAYLIST + "/" + PlaylistId);
	}
	
}
