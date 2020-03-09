Feature: Rate TV Shows
  As a user,
  i want to add and delete
  rates of tv shows i've watched

  Background: Test Authentication
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And A new request session needs to be created
    And The user send a request to session
    Then The session is generated


  Scenario: Rate a tv show
    Given The tv show with data already exist
      | id      |
      | 1044    |
    When The user send a request to rate the tv show with its data
      | value|
      | 9    |
    And The response status message is "Success."
    And The user send a request to delete the session
    And The service responds with a status code "200"


  Scenario: Delete a rating
    Given The tv show with data already exist
      | id      |
      | 1044    |
    When The user send a request to delete the rated tv show
    And The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a tv show with a higher rating than accepted
    Given The tv show with data already exist
      | id        |
      | 66732     |
    When The user send a request to rate the tv show with its data
      | value   |
      | 10.5    |
    And The response status message is "Value too high: Value must be less than, or equal to 10.0."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a tv show with a lower rating than accepted
    Given The tv show with data already exist
      | id        |
      | 66732     |
    When The user send a request to rate the tv show with its data
      | value |
      | -1    |
    And The response status message is "Value too low: Value must be greater than 0.0."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a tv show with a rating not multiple of 0.50
    Given The tv show with data already exist
      | id        |
      | 66732     |
    When The user send a request to rate the tv show with its data
      | value  |
      | 7.2    |
    And The response status message is "Value invalid: Values must be a multiple of 0.50."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Rate a tv show with invalid id
    Given The tv show with data already exist
      | id      |
      | 176     |
    When The user send a request to rate the tv show with its data
      | value|
      | 9    |
    And The response status message is "The resource you requested could not be found."
    And The user send a request to delete the session
    And The service responds with a status code "200"