Feature: Manage Tasks in Salesforce

  @CreateContact @Acceptance
  Scenario: User creates a new user contact
    Given user specifies new contact
    | lastName   |  testName22   |
    When User send de request post to contact endpoint
    Then User get a "201" status code

  @CreateContact @funtional
  Scenario: User creates a new user contact with firstName
    Given User specifies new contact with firstName
      | lastName   |  testName22   |
      | firstname  |  testfirstNames22  |
    When User send de request post to contact endpoint with firstName
    Then User get a "201" status code with firstName

  @CreateContact @funtional
  Scenario: User creates a new user contact with firstName, email
    Given User specifies new contact with firstName, email
      | lastName   |  testName22            |
      | firstname  |  testfirstName22       |
      | email      |  testmasdil@ssdf22.com |
    When User send de request post to contact endpoint with firstName, email
    Then User get a "201" status code with firstName, email

  @CreateContact @funtional
  Scenario: User creates a new user contact with firstName, email, title
    Given User specifies new contact with firstName, email, title
      | lastName   |  testName22            |
      | firstname  |  testfirstName22       |
      | email      |  testemail7@asdf22.com |
      | title      |  enginer               |
    When User send de request post to contact endpoint with firstName, email, title
    Then User get a "201" status code with firstName, email, title
