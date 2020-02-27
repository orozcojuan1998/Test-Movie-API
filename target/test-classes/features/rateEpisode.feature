Feature: Rate TV Episose
  As a user,
  i want to add and delete
  rates of tv shows episodes that i've watched

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


  Scenario: Rate a episode
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 1425   |        3      |        6       |
    When The user send a request to rate the tv show episode with its data
      | value|
      | 9    |
    Then The service responds with a status code "201"
    And The response status message is "Success."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Delete a rating
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 1425   |        3      |        5       |
    When The user send a request to delete the rated tv episode
    Then The service responds with a status code "200"
    And The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"

