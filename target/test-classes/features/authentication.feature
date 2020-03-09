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



