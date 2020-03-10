Feature: Rate tv episodes guest

  Background: Guest Authentication
    Given A new guest session needs to be created
    And The guest sends the request to create the session

  Scenario: Test Rate a tv show episode as a guest
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        15      |       10      |
    When The guest send a request to rate the tv show episode with its data
      | value|
      | 9    |
    Then The response status message is "Success."


  Scenario: Test Delete a tv show episode rating as a guest
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        15     |        10      |
    When The guest send a request to delete the rated tv episode
    Then The response status message is "The item/record was deleted successfully."


  Scenario: Test to rate a tv show episode as a guest with a rating not multiple of 0.50
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        20      |       11      |
    When The guest send a request to rate the tv show episode with its data
      | value|
      | 7.2  |
    Then The response status message is "Value invalid: Values must be a multiple of 0.50."


  Scenario: Test to rate a tv show episode as a guest with a greater rating than accepted
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        20      |       11      |
    When The guest send a request to rate the tv show episode with its data
      | value|
      | 10.5 |
    Then The response status message is "Value too high: Value must be less than, or equal to 10.0."


  Scenario: Test to rate a tv show episode as a guest with a lower rating than accepted
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        20      |       11      |
    When The guest send a request to rate the tv show episode with its data
      | value|
      | 0    |
    Then The response status message is "Value too low: Value must be greater than 0.0."


  Scenario: Test Guest rate a tv show  with invalid episode
    Given The tv show episode with data already exist
      | id     | season_number | episode_number |
      | 2190   |        15      |       60      |
    When The guest send a request to rate the tv show episode with its data
      | value|
      | 9    |
    Then The response status message is "The resource you requested could not be found."


