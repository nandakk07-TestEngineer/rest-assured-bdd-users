Feature: Get Single User
  As an API consumer
  I want to retrieve a specific user via GET /users/{id}

  @smoke @regression
  Scenario: Successfully retrieve an existing user by ID
    When I send a GET request to retrieve user with ID "ca7c4d9c-7ba2-4f6f-a69e-a14cdbb67af2"
    Then the response status code should be 200
    And the response should contain the user details
