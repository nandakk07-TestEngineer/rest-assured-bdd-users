package com.automation.api.endpoints;

import static io.restassured.RestAssured.*;
import com.automation.models.User;
import com.automation.utils.RequestSpecificationBuilder;

import io.restassured.response.Response;

public class UserEndpoints {
	
	private  UserEndpoints() {}
	
	public static Response createUser(User user) {
		return given()
				.spec(RequestSpecificationBuilder.build())
				.body(user)
				.when()
				.post("/users")
				.then()
				.extract().response();
	}
	
	public static Response updateUser(User user, String userId) {
		return given().log().all()
				.spec(RequestSpecificationBuilder.build())
				.body(user)
				.when().log().all()
				.put("/users/"+userId)
				.then().log().all()
				.extract()
				.response();
	}
	public static Response deleteUser(int userId) {
		return given()
					.spec(RequestSpecificationBuilder.build())
					.pathParam("id",userId)
			 .when()	
			 		.delete("/users/{id}")
			 .then()
			 		.extract().response();
	}
	
	public static Response getUserDetails(String userId) {
		return given()
				.spec(RequestSpecificationBuilder.build())
				.pathParam("id", userId)
				.when()
				.get("/users/{id}")
				.then()
				.extract()
				.response();
	}
	
	public static Response getUsers() {
		return given()
				.spec(RequestSpecificationBuilder.build())
				.when()
				.get("/users")
				.then()
				.extract()
				.response();
	}
}
