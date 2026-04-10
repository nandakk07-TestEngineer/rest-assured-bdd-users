Feature: Get All Users
  As an API consumer
  I want to retrieve all users via GET /users


  @smoke @regression
  Scenario: Successfully retrieve all users (default page)
    When I send a GET request to retrieve all users
    Then the response status code should be 200
    And the response should contain a list of users
    And each user in the list should have an ID, name and age

