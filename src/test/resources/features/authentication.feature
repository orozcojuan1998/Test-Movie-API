Feature: Authentication
  As a user
  I want to authenticate in the system
  So that I can perform actions in the system


  Scenario: Test request token creation
    Given A new request token needs to be created
    When The user send a request to create the request token
    Then The token is generated
    And The service responds with a status code "200"
    And The response contains the field success equals to "true"


  Scenario: Create a session with login
    Given A new session with login needs to be created
    And A new request token needs to be created
    And The user send a request to create the request token
    And The token is generated
    When The user send a request to create the session with login
    Then The service responds with a status code "200"
    And The response contains the field success equals to "true"

  Scenario: Create a new session
    Given A new request session needs to be created
    And A new request token needs to be created
    And The user send a request to create the request token
    And The token is generated
    When The user send a request to session
    Then The service responds with a status code "200"
    And The response contains the session id


  Scenario: Test session delete 
    Given That i want to delete an existing session id
    When The user send a request to delete the session
    Then The service responds with a status code "200"
    And The response contains the field success equals to "<string>"
    
