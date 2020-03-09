Feature: Reviews
  As a user i want to
  check the details of
  the reviews in the system

  Scenario: Test check review details
    Given The review exist with its data
    |     id                   |
    | 5d6fc492e22d28000fb54250 |
    When The user send a request to get the review details
    Then The response content of the review is not empty

  Scenario: Test check review details with invalid review id
    Given The review exist with its data
      |     id           |
      | 5d6fc492e22d     |
    When The user send a request to get the review details
    Then The response status message is "404"