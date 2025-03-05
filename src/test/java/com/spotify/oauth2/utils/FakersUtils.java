package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakersUtils {

	
	public static String generateName() {
		Faker faker = new Faker();
	return "Playlist "+faker.regexify("a-zA-Z,-_{20}");
	}
	
	public static String generateDescription() {
		Faker faker = new Faker();
		return "description "+faker.regexify("a-zA-Z,!@#$%^&{10}");
	}
}
