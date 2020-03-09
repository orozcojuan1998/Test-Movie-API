Feature: Rate Movies
  As a user,
  i want to add and delete
  rates of movies i've watched

  Background: Test Authentication
    Given A new request token needs to be created
    And The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And A new request session needs to be created
    And The user send a request to session
    And The session is generated


    Scenario: Rate a movie
      Given The movie with data already exist
      | id        |
      |  34769    |
      When The user send a request to rate the movie with its data
      | value|
      | 9.0  |
      Then The response status message is "Success."
      And The user send a request to delete the session
      And The service responds with a status code "200"


  Scenario: Delete a rating of a movie
    Given The movie with data already exist
      | id        |
      | 34769     |
    When The user send a request to delete the rated movie
    Then The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a movie with a higher rating than accepted
    Given The movie with data already exist
      | id        |
      |  338762   |
    When The user send a request to rate the movie with its data
      | value |
      | 10.5  |
    Then The response status message is "Value too high: Value must be less than, or equal to 10.0."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a movie with a lower rating than accepted
    Given The movie with data already exist
      | id        |
      |  338762   |
    When The user send a request to rate the movie with its data
      | value|
      | 0    |
    Then The response status message is "Value too low: Value must be greater than 0.0."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a movie with a rating not multiple of 0.50
    Given The movie with data already exist
      | id        |
      |  338762   |
    When The user send a request to rate the movie with its data
      | value|
      | 7.2  |
    Then The response status message is "Value invalid: Values must be a multiple of 0.50."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a movie with invalid movie id
    Given The movie with data already exist
      |  id       |
      |  359      |
    When The user send a request to rate the movie with its data
      | value|
      | 9.0  |
    Then The response status message is "The resource you requested could not be found."
    And The user send a request to delete the session
    And The service responds with a status code "200"





