Feature: Manage Tasks in Salesforce

  @CreateContact @Acceptance
  Scenario: user creates a new user contact
    Given user specifies new contact
    | lastName   |  testName22   |
    When user send request post to contact endpoint
    Then the status code is 201

  @CreateContact @funtional
  Scenario: user creates a new user contact with firstName
    Given user specifies new contact with firstName
      | lastName   |  testName22   |
      | firstname  |  testfirstNames22  |
    When user send de request post to contact endpoint with firstName
    Then the status code is 201

  @CreateContact @funtional
  Scenario: user creates a new user contact with firstName, email
    Given user specifies new contact with firstName, email
      | lastName   |  testName22            |
      | firstname  |  testfirstName22       |
      | email      |  testmasdil@ssdf22.com |
    When user send de request post to contact endpoint with firstName, email
    Then the status code is 201

  @CreateContact @funtional
  Scenario: User creates a new user contact with firstName, email, title
    Given user specifies new contact with firstName, email, title
      | lastName   |  testName22            |
      | firstname  |  testfirstName22       |
      | email      |  testemail7@asdf22.com |
      | title      |  enginer               |
    When user send de request post to contact endpoint with firstName, email, title
    Then the status code is 201

  @CreateContact @funtional
  Scenario: User creates a new user contact with firstName, email, title, etc
    Given user specifies new contact with firstName, email, title, others.
      | lastName      |  testName22            |
      | firstname     |  testfirstName22       |
      | email         |  testemail7@asdf22.com |
      | title         |  enginer               |
      | HomePhone     |  17927347129412421834  |
      | Department    |  Finanzas              |
      | OtherPhone    |  17927347129412421834  |
      | Description   |  testDescription       |
      | AssistantName |  testAsistanceName     |
      | MobilePhone   |  17927347129412421834  |

    When user send de request post to contact endpoint with firstName, email, title, other
    Then the status code is 201
