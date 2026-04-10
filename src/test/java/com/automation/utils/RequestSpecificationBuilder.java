package com.automation.utils;

import com.automation.config.ConfigManager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {
	
	private RequestSpecificationBuilder() {
		
	}
	
	public static RequestSpecification build() {
		return new RequestSpecBuilder()
						.setBaseUri(ConfigManager.getConfig().baseUrl())
						.setContentType(ContentType.JSON)
						.setAccept(ContentType.JSON)
						.build();
	}
	
	public static RequestSpecification buildWithAuth(String token) {
		return new RequestSpecBuilder()
					.addRequestSpecification(build())
					.addHeader("Authorization", "Bearer " + token)
					.build();
	}
}
