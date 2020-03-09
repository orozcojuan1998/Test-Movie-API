Feature: Rate TV Episose
  As a user,
  i want to add and delete
  rates of tv shows episodes that i've watched

  Background: Test Authentication
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And A new request session needs to be created
    And The user send a request to session
    Then The session is generated


  Scenario: Rate a episode
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        23      |       6       |
    When The user send a request to rate the tv show episode with its data
      | value|
      | 9    |
    And The response status message is "Success."
    And The user send a request to delete the session
    Then The service responds with a status code "200"

  Scenario: Delete a rating
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        23     |        5       |
    When The user send a request to delete the rated tv episode
    And The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    Then The service responds with a status code "200"

