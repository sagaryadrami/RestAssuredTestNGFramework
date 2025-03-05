package com.spotify.oauth2.utils;

public enum StatusCode {

	CODE_200(200,""),
	CODE_201(201,""),
	CODE_400(400,"Missing required field: name"),
	CODE_401(401,"Invalid access token");
	
	public final int code;
	public final String txt;
	
	StatusCode(int code,String txt){
		this.code=code;
		this.txt=txt;
	}
	
//	public int getcode(){
//		return code;
//	}
//	
//	public String gettxt() {
//		return txt;
//	}


}
