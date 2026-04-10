package com.automation.utils;

import java.util.HashMap;
import java.util.Map;

import com.automation.models.User;

import io.restassured.response.Response;

public class ScenarioContext {
	private Response response;
	private User currentUser;
	
	private final Map<String,Object> context = new HashMap<>();
	
	public void setResponse(Response response) {
		this.response = response;
	}
	
	public Response getResponse() {
		return response;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void set(String key, Object value) {
		context.put(key, value);
	}
	
	public Object get(String key) {
		return context.get(key);
	}
	
	public String getString(String key) {
		Object val = context.get(key);
		return val != null?val.toString():null;
	}
	
	public Integer getInt(String key) {
		Object val = context.get(key);
		if(val instanceof Integer) return (Integer) val;
		if(val != null) return Integer.parseInt(val.toString());
		return null;
	}
	
}
