Feature: Create User
	As an API consumer
	I want to create a new user via POST /users
	
	Background: Given the user API is available
	
	@smoke @regression
	Scenario: Successfully created a user with valid payload
		Given I have a valid user payload
		When I send the POST request to create the user
		Then the response status code should be 201
		And the response body should contain created user details
		And the response should contain a non-null user id
	
	@regression	
	Scenario: Create a user with explicit name and job
		Given I have a user payload with name "Preeti Sharma", age 38 and job "Senior QA Engineer"
		When I send the POST request to create the user
		Then the response status code should be 201
		And the response body should contain created user details
		
	@regression
	Scenario Outline: Create multiple users with different roles
		Given I have a user payload with name "<name>", age <age> and job "<job>"
		When I send the POST request to create the user 
		Then the response status code should be 201
		And the response body should contain created user details
		
		Examples:
			| name 					|job				| age  | 
			| David Paul 			|Data Scientist 	| 32   |
			| Monica Geller  		|Chef			 	| 28   |
			| Rachel Green 			|Marketing Head		| 29   |
			| Bob Martin			|Developer			| 35   |


	
