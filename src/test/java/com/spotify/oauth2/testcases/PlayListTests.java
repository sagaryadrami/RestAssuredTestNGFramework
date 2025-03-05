package com.spotify.oauth2.testcases;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.applicationAPI.PlaylistApi;
import com.spotify.oauth2.pojo.Errormain;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakersUtils;
import com.spotify.oauth2.utils.StatusCode;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlayListTests extends BaseTest{
	
	
	@Story("create a playlist")
	@Description("this is demo description")
	@Test(description = "Creating a playlist")
	public void ShouldBeAbleToCreateAPlaylist() {
		Playlist requestPlaylist = playlistBuilder(FakersUtils.generateName(),FakersUtils.generateDescription(),false);
		
		
		//sending body through serialized pojo
//		Playlist requestPlaylist = new Playlist().setDescription("New playlist description").setName("New Playlist")
//				.setPublic(false);
		
		Response response = PlaylistApi.post(requestPlaylist);
		assertStatusCode(response.statusCode(),StatusCode.CODE_201);
		//doing deserializing
		Playlist responsePlaylist = response.as(Playlist.class);
		//assertions(throught deserialized pojo class)
		assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);	
	}
	
	@Test
	public void ShouldBeAbleGetPlayList() {
		
		Playlist requestPlaylist = playlistBuilder("updated playlist description","updated Playlist name",false);
		
//		Playlist requestPlaylist = new Playlist().setDescription("updated playlist description").setName("updated Playlist name")
//				.setPublic(false);
		
		Response response = PlaylistApi.get(DataLoader.getInstances().getGetPlaylistId());
		assertStatusCode(response.statusCode(),StatusCode.CODE_200);
		//assertThat(response.statusCode(),equalTo(200));
		
	}
	
	@Test
	public void ShouldBeAbleToUpdatePlaylist() {
		
		Playlist requestPlaylist = playlistBuilder("Updated playlist description","Updated Playlist Name(sagar-yadrami)",false);
		
//		Playlist requestPlaylist = new Playlist().setName("Updated Playlist Name(sagar-yadrami)").setDescription("Updated playlist description")
//				.setPublic(false);
	
		Response response = PlaylistApi.put(requestPlaylist, DataLoader.getInstances().getUpdatePlaylistId());
		assertStatusCode(response.statusCode(),StatusCode.CODE_200);
	}
	
	//negative scenario
	@Story("create a playlist")
	@Test
	public void ShouldNotBeAbleToCreatePlayList() {
		
		
		Playlist requestPlaylist = new Playlist();
		requestPlaylist.setDescription(FakersUtils.generateDescription());
		requestPlaylist.set_public(false);
		Response response = PlaylistApi.post(requestPlaylist);
		assertStatusCode(response.statusCode(),StatusCode.CODE_400);
		assertError(response.as(Errormain.class),StatusCode.CODE_400);
	}
	
	//negative scenario
	@Story("create a playlist")
	@Test
	public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
		
		String invalid_token="12345";
		
		Playlist playlist = playlistBuilder(FakersUtils.generateDescription(),FakersUtils.generateName(),false);
		
		//Playlist playlist = new Playlist().setName("New Playlist").setDescription("New playlist description")
		//		.setPublic(false);
		
		Response response = PlaylistApi.post(playlist,invalid_token );
		//assertThat(response.statusCode(),equalTo(401));
		assertStatusCode(response.statusCode(),StatusCode.CODE_401);
		
	
		assertError(response.as(Errormain.class),StatusCode.CODE_401);
	}
	

	
	//written method to make test cases line reduce

	@Step
	public Playlist playlistBuilder(String description,String name, boolean _public) {
		return Playlist.builder().description(description).name(name)._public(_public).build();
//		Playlist playlist = new Playlist();
//		playlist.setName(name);
//		playlist.setDescription(description);
//		playlist.set_public(_public);
//		return playlist;
	}
	
	@Step
	public void assertPlaylistEqual(Playlist responsePlaylist , Playlist requestPlaylist) {
		assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));
	}
	
	@Step
	public void assertStatusCode(int actualStatuscode,StatusCode statuscode) {
		assertThat(actualStatuscode,equalTo(statuscode.code));
	}
	
	@Step
	public void assertError(Errormain errormain,StatusCode statuscode) {
		assertThat(errormain.getError().getStatus(),equalTo(statuscode.code));
		assertThat(errormain.getError().getMessage(),equalTo(statuscode.txt));
	}
	
	
}
