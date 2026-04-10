Feature: Delete User
  As an API consumer
  I want to delete an existing user via DELETE /users/{id}

  @smoke @regression
  Scenario: Successfully delete a user by ID
    When I send a DELETE request for user with ID 2
    Then the response status code should be 404
    And the response body return user not found

