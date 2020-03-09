Feature: Rate Movies Guest

  Background: Guest Authentication
    Given A new guest session needs to be created
    And The guest sends the request to create the session

  Scenario: Test rate a movie as a guest
    Given The movie with data already exist
      | id     |
      | 581600 |
    When The guest send a request to rate the movie with its data
      | value |
      | 9.0   |
    Then The response status message is "Success."


  Scenario: Test delete a rating of a movie
    Given The movie with data already exist
      | id     |
      | 581600 |
    When The guest send a request to delete the rated movie
    Then The response status message is "The item/record was deleted successfully."


  Scenario: Test to rate a movie with a higher rating than accepted
    Given The movie with data already exist
      | id     |
      | 338762 |
    When The guest send a request to rate the movie with its data
      | value |
      | 10.5  |
    Then The response status message is "Value too high: Value must be less than, or equal to 10.0."


  Scenario:Test to rate a movie with a lower rating than accepted
    Given The movie with data already exist
      | id     |
      | 338762 |
    When The guest send a request to rate the movie with its data
      | value |
      | 0     |
    Then The response status message is "Value too low: Value must be greater than 0.0."


  Scenario:Test to rate a movie with a rating not multiple of 0.50
    Given The movie with data already exist
      | id     |
      | 338762 |
    When The guest send a request to rate the movie with its data
      | value |
      | 7.2   |
    Then The response status message is "Value invalid: Values must be a multiple of 0.50."


  Scenario:Test to rate a movie with invalid movie id
    Given The movie with data already exist
      | id  |
      | 359 |
    When The guest send a request to rate the movie with its data
      | value |
      | 9.0   |
    Then The response status message is "The resource you requested could not be found."
