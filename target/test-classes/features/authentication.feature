Feature: Authentication
  As a user
  I want to authenticate in the system
  So that I can perform actions in the system


  Scenario: Test Authentication
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And A new request session needs to be created
    And The user send a request to session
    Then The session is generated

  Scenario: Test Delete of session
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login
    And A new request session needs to be created
    And The user send a request to session
    Then The session is generated
    And The user send a request to delete the session
    And The service responds with a status code "200"

  Scenario: Test Token Creation with invalid API KEY
    Given A new request token needs to be created
    When The user send a request to create the request token with invalid key
    Then The response contains the field success equals to "false"


  Scenario: Test Validate Token Creation with invalid username
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created with invalid data
    And The user send a request to create the session with login
    Then The response status message is "Invalid username and/or password: You did not provide a valid login."

  Scenario: Test Validate Token Creation with invalid password
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created with invalid password
    And The user send a request to create the session with login
    Then The response status message is "Invalid username and/or password: You did not provide a valid login."

  Scenario: Test Validate Token Creation with empty username
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login with empty username
    Then The response status message is "You must provide a username and password."

  Scenario: Test Validate Token Creation with empty password
    Given A new request token needs to be created
    When The user send a request to create the request token
    And The token is generated
    And A new session with login needs to be created
    And The user send a request to create the session with login with empty password
    Then The response status message is "You must provide a username and password."






