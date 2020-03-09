Feature: Rate Movies
  As a guest,
  i want to add and delete
  rates of movies i've watched

  Background: Test Guest Authentication
    Given A new guest session needs to be created
    When The guest sends the request to create the session
    Then The response contains the field success equals to "true"


    Scenario: Rate a movie
      Given The movie with data already exist
      | id        |
      |  581600   |
      When The guest send a request to rate the movie with its data
      | value|
      | 9.0  |
      Then The response status message is "Success."



  Scenario: Delete a rating of a movie
    Given The movie with data already exist
      | id        |
      | 581600    |
    When The guest send a request to delete the rated movie
    Then The response status message is "The item/record was deleted successfully."


  Scenario: Rate a movie with a higher rating than accepted
    Given The movie with data already exist
      | id        |
      |  338762   |
    When The guest send a request to rate the movie with its data
      | value |
      | 10.5  |
    Then The response status message is "Value too high: Value must be less than, or equal to 10.0."


  Scenario: Rate a movie with a lower rating than accepted
    Given The movie with data already exist
      | id        |
      |  338762   |
    When The guest send a request to rate the movie with its data
      | value|
      | 0    |
    Then The response status message is "Value too low: Value must be greater than 0.0."


  Scenario: Rate a movie with a rating not multiple of 0.50
    Given The movie with data already exist
      | id        |
      |  338762   |
    When The guest send a request to rate the movie with its data
      | value|
      | 7.2  |
    Then The response status message is "Value invalid: Values must be a multiple of 0.50."


  Scenario: Rate a movie with invalid movie id
    Given The movie with data already exist
      |  id       |
      |  359      |
    When The guest send a request to rate the movie with its data
      | value|
      | 9.0  |
    Then The response status message is "The resource you requested could not be found."
