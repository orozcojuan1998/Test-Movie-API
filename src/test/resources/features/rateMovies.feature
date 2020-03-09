Feature: Rate Movies
  As a user,
  i want to add and delete
  rates of movies i've watched

  Background: Test Authentication
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And A new request session needs to be created
    And The user send a request to session
    Then The session is generated


    Scenario: Rate a movie
      Given The movie with data already exist
      | id        |
      |  338762   |
      When The user send a request to rate the movie with its data
      | value|
      | 9.0  |
      Then The response status message is "Success."
      And The user send a request to delete the session
      And The service responds with a status code "200"


  Scenario: Delete a rating
    Given The movie with data already exist
      | id       |
      | 338762    |
    When The user send a request to delete the rated movie
    Then The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"

