Feature: Manage Tasks in Salesforce
  Background: User is authenticated
    Given a user sing in the web page SalesForce

  @Acceptance
  Scenario: User creates a new user contact
    Given user specifies new contact
      | lastName   |  testName   |
    When User send de request post to contact endpoint
    Then User get a "201" status code
  @funtional
  Scenario: User creates a new user contact with firstName
    Given User specifies new contact with firstName
      | lastName   |  testName   |
      | fistname   |  testfirstName  |
    When User send de request post to contact endpoint with firstName
    Then User get a "201" status code with firstName

  @funtional
  Scenario: User creates a new user contact with firstName, email
    Given User specifies new contact with firstName, email
      | lastName   |  testName   |
      | firstname  |  testfirstName  |
      | email      |  testemail  |
    When User send de request post to contact endpoint with firstName, email
    Then User get a "201" status code with firstName, email

  @funtional
  Scenario: User creates a new user contact with firstName, email, title
    Given User specifies new contact with firstName, email, title
      | lastName   |  testName   |
      | firstname  |  testfirstName  |
      | email   |  testemail  |
      | title   |  title |
    When User send de request post to contact endpoint with firstName, email, title
    Then User get a "201" status code with firstName, email, title
