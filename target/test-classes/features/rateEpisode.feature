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


  Scenario: Test Rate a tv show episode
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        22      |       11      |
    When The user send a request to rate the tv show episode with its data
      | value|
      | 9    |
    Then The response status message is "Success."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test Delete a tv show episode rating
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        22     |        10      |
    When The user send a request to delete the rated tv episode
    Then The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test to rate a tv show episode with a rating not multiple of 0.50
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        22      |       11      |
    When The user send a request to rate the tv show episode with its data
      | value|
      | 7.2  |
    Then The response status message is "Value invalid: Values must be a multiple of 0.50."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test to rate a tv show episode with a greater rating than accepted
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        22      |       11      |
    When The user send a request to rate the tv show episode with its data
      | value|
      | 10.5 |
    Then The response status message is "Value too high: Value must be less than, or equal to 10.0."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test to rate a tv show episode with a lower rating than accepted
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        22      |       11      |
    When The user send a request to rate the tv show episode with its data
      | value|
      | 0    |
    Then The response status message is "Value too low: Value must be greater than 0.0."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test Rate a tv show with invalid episode
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        10      |       60      |
    When The user send a request to rate the tv show episode with its data
      | value|
      | 9    |
    Then The response status message is "The resource you requested could not be found."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test Rate a tv show with invalid API KEY
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        10      |       10      |
    When The user send a request to rate the tv show episode with its invalid api key
      | value|
      | 9    |
    Then The response status message is "Invalid API key: You must be granted a valid key."
    And The user send a request to delete the session
    And The service responds with a status code "200"

