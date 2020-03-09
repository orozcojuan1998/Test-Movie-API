Feature: Rate TV Shows
  As a user,
  i want to add and delete
  rates of tv shows i've watched

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


  Scenario: Rate a tv show
    Given The tv show with data already exist
      | id      |
      | 57243   |
    When The user send a request to rate the tv show with its data
      | value|
      | 9    |
    Then The service responds with a status code "201"
    And The response status message is "Success."
    And The user send a request to delete the session
    And The service responds with a status code "200"


  Scenario: Delete a rating
    Given The tv show with data already exist
      | id      |
      | 57243   |
    When The user send a request to delete the rated tv show
    Then The service responds with a status code "200"
    And The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"

