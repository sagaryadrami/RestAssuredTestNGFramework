package com.spotify.oauth2.testcases;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("Starting TEST : " + m.getName());
		System.out.println("Thread ID : " + Thread.currentThread().getId());
		
	}

}
