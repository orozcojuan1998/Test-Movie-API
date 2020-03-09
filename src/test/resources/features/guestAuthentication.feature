Feature: Guest Authentication
  As a guest
  I want to authenticate in the system
  So that I can perform actions in the system

  Scenario: Test Guest Session Authentication
    Given A new guest session needs to be created
    When The guest sends the request to create the session
    Then The response contains the field success equals to "true"
