package com.automation.stepDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automation.api.endpoints.UserEndpoints;
import com.automation.models.User;
import com.automation.models.UserListResponse;
import com.automation.utils.ScenarioContext;
import com.automation.utils.SchemaValidator;
import com.automation.utils.TestDataFactory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserDefinitions {
	
	private static final Logger log = LogManager.getLogger(UserDefinitions.class);
	private final ScenarioContext ctx;
	
	public UserDefinitions(ScenarioContext ctx){
		this.ctx = ctx;
	}
	
	@Given("I have a valid user payload")
	public void createUserPayload() {
		User user = TestDataFactory.createUserPayload();
		ctx.setCurrentUser(user);
		log.info("Built user payload: ",user);
	}
	
	@Given("I have a user payload with name {string}, age {int} and job {string}")
	public void requestedUserPayload(String name,int age , String job) {
		User user = TestDataFactory.CreateUserPayload(name,age,job);
		ctx.setCurrentUser(user);
		log.info("Build explicit payload :", user);
	}
	
	@Given("I have an update user payload")
	public void updateUserPayload() {
	    User user = TestDataFactory.updateUserPayload();
	    System.out.println(user.toString());
	    ctx.setCurrentUser(user);
	    log.info("Built update payload");
	} 
		
	
	@When("I send the POST request to create the user")
	public void postApiCall() {
		Response createUserResponse = UserEndpoints.createUser(ctx.getCurrentUser());
		ctx.setResponse(createUserResponse);
		log.info("POST/users StatusCode: ", createUserResponse.getStatusCode());
		log.info("POST/users Response: ", createUserResponse.getBody());
	}
	
	@When("I send the PUT request to update the user with ID {string}")
	public void putAPICall(String userId) {
		Response updateUserResponse = UserEndpoints.updateUser(ctx.getCurrentUser(), userId);
		ctx.setResponse(updateUserResponse);
		log.info("PUT/users StatusCode: ", updateUserResponse.getStatusCode());
		log.info("PUT/users Response: ", updateUserResponse.getBody());
	}
	
	@When("I send a GET request to retrieve all users")
    public void getListCall() {
        Response response = UserEndpoints.getUsers();
        ctx.setResponse(response);
        log.info("GET /users → Status: {}", response.getStatusCode());
    }
	
	@When("I send a GET request to retrieve user with ID {string}")
    public void getSingleUSer(String userId) {
        Response response = UserEndpoints.getUserDetails(userId);
        ctx.setResponse(response);
        ctx.set("requestedUserId", userId);
        log.info("GET /users/{} → Status: {}", userId, response.getStatusCode());
    }
	
	 @When("I send a DELETE request for user with ID {int}")
	 public void iSendADeleteRequestForUserWithId(int userId) {
	     Response response = UserEndpoints.deleteUser(userId);
	     ctx.setResponse(response);
	     ctx.set("deletedUserId", userId);
	     log.info("DELETE /users/{} → Status: {}", userId, response.getStatusCode());
	 }

	
	@Then("the response status code should be {int}")
	public void validateStatusCode(int expectedStatusCode) {
		int actualStatusCode = ctx.getResponse().getStatusCode();
		assertThat(actualStatusCode).isEqualTo(expectedStatusCode);
		log.info("Status Code Matched");
	}
	
	@Then("the response body should contain created user details")
	public void responseBodyValidation() {
		User responseUser = ctx.getResponse().as(User.class);
		User requestUser = ctx.getCurrentUser();
		
		assertThat(responseUser.getName()).isEqualTo(requestUser.getName());
		assertThat(responseUser.getJob()).isEqualTo(requestUser.getJob());
		assertThat(responseUser.getAge()).isEqualTo(requestUser.getAge());
		assertThat(responseUser.getId()).isNotNull();
	}
	
	@Then("the response should contain a non-null user id")
	public void userIdValidation() {
		SchemaValidator.assertFieldNotEmpty(ctx.getResponse(), "id");  
	}
		
    @Then("the response should contain a list of users")
    public void validateListReturned() {
        UserListResponse listResponse = ctx.getResponse().as(UserListResponse.class);

        assertThat(listResponse.getData()).isNotNull().isNotEmpty();
        assertThat(listResponse.getPage()).isPositive();
        assertThat(listResponse.getTotal()).isPositive();

        log.info("Retrieved {} users on page {} of {}",
                listResponse.getData().size(), listResponse.getPage(), listResponse.getTotal());
    }
    
    @And("each user in the list should have an ID, name and age")
    public void validateListObjects() {
        UserListResponse listResponse = ctx.getResponse().as(UserListResponse.class);
        listResponse.getData().forEach(user -> {
            assertThat(user.getId()).isNotNull();
            assertThat(user.getName()).isNotNull().isNotEmpty();
            assertThat(user.getAge()).isNotNull();
        });
        log.info("All users have required fields (id, name, age)");
    }

    @Then("the response body return user not found")
    public void deleteNotFound() {
        String expected = "{\"message\":\"User not found\"}";
        String actual = ctx.getResponse().getBody().asString();
        assertThat(actual).isEqualTo(expected);
        log.info("Response body is empty (as expected for DELETE)");
    }
		
    @Then("the response should contain the user details")
    public void theResponseShouldContainTheUserDetails() {
        User user = ctx.getResponse().as(User.class);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(ctx.getString("requestedUserId"));
        assertThat(user.getEmail()).isNotNull().isNotEmpty();
        assertThat(user.getAge()).isNotNull();
        assertThat(user.getJob()).isNotNull().isNotEmpty();
        log.info(" User {} retrieved: {} {}");
    }

	
}
