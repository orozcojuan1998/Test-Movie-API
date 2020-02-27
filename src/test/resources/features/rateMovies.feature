Feature: Rate Movies
  As a user,
  i want to add and delete
  rates of movies i've watched

  Background: Test Authentication
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


    Scenario: Rate a movie
      Given The movie with data already exist
      | id     |
      | 64956  |
      When The user send a request to rate the movie with its data
      | value|
      | 9.0  |
      Then The service responds with a status code "201"
      And The response status message is "Success."

  Scenario: Delete a rating
    Given The movie with data already exist
      | id     |
      | 800    |
    When The user send a request to delete the rated movie
    Then The service responds with a status code "200"
    And The response status message is "The item/record was deleted successfully."