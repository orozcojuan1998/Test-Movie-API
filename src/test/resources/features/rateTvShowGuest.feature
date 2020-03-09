Feature: Rate Movies Guest

  Background: Guest Authentication
    Given A new guest session needs to be created
    And The guest sends the request to create the session

  Scenario: Test rate a tv show as a guest
    Given The tv show with data already exist
      | id     |
      | 38472  |
    When The guest send a request to rate the tv show with its data
      | value |
      | 9.0   |
    Then The response status message is "Success."


  Scenario: Test delete a tv show as a guest
    Given The tv show with data already exist
      | id     |
      | 38472  |
    When The guest send a request to delete the rated tv show
    Then The response status message is "The item/record was deleted successfully."


  Scenario: Test to rate a tv show as a guest with a higher rating than accepted
    Given The tv show with data already exist
      | id     |
      | 56296  |
    When The guest send a request to rate the tv show with its data
      | value |
      | 10.5  |
    Then The response status message is "Value too high: Value must be less than, or equal to 10.0."


  Scenario:Test to rate a tv show as a guest with a lower rating than accepted
    Given The tv show with data already exist
      | id     |
      | 56296  |
    When The guest send a request to rate the tv show with its data
      | value |
      | 0     |
    Then The response status message is "Value too low: Value must be greater than 0.0."


  Scenario:Test to rate a tv show as a guest with a rating not multiple of 0.50
    Given The tv show with data already exist
      | id     |
      | 56296  |
    When The guest send a request to rate the tv show with its data
      | value |
      | 7.2   |
    Then The response status message is "Value invalid: Values must be a multiple of 0.50."


  Scenario:Test to rate a tv show as a guest with invalid tv show id
    Given The tv show with data already exist
      | id     |
      | 111084 |
    When The guest send a request to rate the tv show with its data
      | value |
      | 9.0   |
    Then The response status message is "The resource you requested could not be found."
