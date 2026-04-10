Feature: Update User
	As an API consumer 
	I want to update an existing user via PUT /users/{id}
	
	@smoke @regression
	Scenario: Successfully update a user using PUT 
		Given I have an update user payload
		When I send the PUT request to update the user with ID "d6db3c9e-404f-4840-ac9b-e3caf7334cc8"
		Then the response status code should be 200
		And the response body should contain created user details
	
	@regression
  	Scenario: Successfully partial update a user using PATCH
    	Given I have a user payload with name "Partial Update Name", age 36 and job "Updated Job Title"
    	When I send the PUT request to update the user with ID "d6db3c9e-404f-4840-ac9b-e3caf7334cc8"
    	Then the response status code should be 200
    	And the response body should contain created user details
    
    @regression
  	Scenario Outline: Update different users with PUT
    	Given I have a user payload with name "<name>", age <age> and job "<job>"
    	When I send the PUT request to update the user with ID "<userId>"
    	Then the response status code should be 200
    	And the response body should contain created user details

    Examples:
      | userId 									  | name             | job                   | age		|
      | 952b81cd-36f6-4798-936a-2c92bf575cb1      | Janet Weaver     | QA Lead               | 41		|
      | 58d0a896-41cd-49d2-949d-0fdf475d843f	  | Eve Holt         | Automation Engineer   | 42		|
      | ca7c4d9c-7ba2-4f6f-a69e-a14cdbb67af2	  | Charles Morris   | Data Engineer         | 43		|
		
	