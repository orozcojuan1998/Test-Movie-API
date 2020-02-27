Feature: List
  As a user
  I want to be able to create,
  edit, add movies and delete my list

  Background: : Test Authentication
    Given A new request token needs to be created
    When The user send a request to create the request token
    Then The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And The response contains the field success equals to "true"
    And A new request session needs to be created
    And The user send a request to session
    And The session is generated
    And The service responds with a status code "200"
    And The response contains the session id

  Scenario: Test get list detail
    Given The user wants to see the details of a list
    When The user send the request to get the list detail
    Then The response contains the name "This is my great first list" of the list

  Scenario: Test create a list
    Given The user wants to create a list
    When The user send the request to create the list with its data
    |name          | description                        |
    | Terror List 3| List of my favorite horror movies 8|
    Then The service responds with a status code "201"

  Scenario: Test delete of a list
    Given The user wants to delete a list
    When The user send the request to create the list with its data
      |name                 | description                       |
      | Movies for me List 1| List of my favorite movies 7      |
    And The user send the request to delete the list
    Then The response status message is "The item/record was updated successfully."

