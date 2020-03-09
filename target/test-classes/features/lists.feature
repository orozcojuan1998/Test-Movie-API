Feature: List
  As a user
  I want to be able to create,
  edit, add movies and delete my list

  Background: : Test Authentication
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And A new request session needs to be created
    And The user send a request to session
    Then The session is generated


  Scenario: Test get list detail
    Given The user wants to see the details of a list
      |      id                 |
      |     133791              |
    When The user send the request to get the list detail
    Then The response contains the name "This is my great first list" of the list
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test create a list
    Given The user wants to create a list
    When The user send the request to create the list with its data
    |name          | description                         |
    | Terror List 9| List of my favorite horror movies 14|
    Then The service responds with a status code "201"
    And The user send a request to delete the session
    And The service responds with a status code "200"


  Scenario: Test delete of a list
    Given The user wants to delete a list
    When The user send the request to create the list with its data
      |name                 | description                       |
      | Movies for me List 2| List of my favorite movies 6      |
    And The user send the request to delete the list
    Then The response status message is "The item/record was updated successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"


  Scenario: Test add a movie to a list
    Given The list already exist with its data
      |      id                 |
      |     134067              |
    When The user send the request to add a movie to a list with its data
      |      id_movie           |
      |     419704              |
    Then The service responds with a status code "201"
    And The user send a request to delete the session
    And The service responds with a status code "200"


  Scenario: Test delete a movie from a list
    Given The list already exist with its data
      |      id                 |
      |     134067              |
    When The user send the request to delete a movie from a list with its data
      |      id_movie           |
      |     419704              |
    Then The service responds with a status code "200"
    And The response status message is "The item/record was deleted successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"


  Scenario: Test to clear a list
    Given The user has the id of the list to clear
      |      id                 |
      |     134062              |
    When The user send the request to clear the list
    Then The service responds with a status code "201"
    And The response status message is "The item/record was updated successfully."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test to clear a nonexistent list
    Given The user has the id of the list to clear
      |      id                 |
      |     570670              |
    When The user send the request to clear the list
    Then The response status message is "The resource you requested could not be found."
    And The user send a request to delete the session
    And The service responds with a status code "200"


  Scenario: Test get check that a movie is in a list
    Given The list already exist with its data
      |      id                 |
      |     134067              |
    When The user send the request to check if a movie is present in the list with its data
      |      id_movie           |
      |     570670              |
    Then The service responds with a status code "200"
    And The response contains the field success equals to "true"
    And The user send a request to delete the session
    And The service responds with a status code "200"

    Scenario: Test get details of a nonexistent list
      Given The user wants to see the details of a list
        |      id                 |
        |     787000              |
      When The user send the request to get the list detail
      Then The response status message is "The resource you requested could not be found."

  Scenario: Test delete an nonexistent list
    Given The user wants to delete a list with its data
      |      id                 |
      |     787000              |
    When The user send the request to delete the list created
    Then The response status message is "The resource you requested could not be found."

  Scenario: Test create a list without session id
    Given The user wants to create a list
    When The user send the request to create the list without session id
      |name          | description                         |
      | Terror List 9| List of my favorite horror movies 14|
    Then The response status message is "Authentication failed: You do not have permissions to access the service."
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test create a list without name
    Given The user wants to create a list
    When The user send the request to create the list without name
      |name          | description                         |
      | ""           | List of my favorite movies 14       |
    Then The service responds with a status code "422"
    And The user send a request to delete the session
    And The service responds with a status code "200"



