Feature: Creation funtional and acceptance

  @AccountCreation
  Scenario: Create an Account with minimun data required
    Given I fill the request with the data required
      | Name | Account536 |
    When I create an Account with the name
    Then the status code is a number 201
    And schema "account creation schema" is valid

  @AccountCreation
  Scenario: Create an Account with five data
    Given I fill the request with the data required
      | Name     | Account536       |
      | Type     | Customer         |
      | Phone    | 101010           |
      | Website  | www.customer.com |
      | Industry | customerINC      |
    When I create an Account with the name
    Then the status code is a number 201
    And schema "account creation schema" is valid

    @AccountCreation
  Scenario: Create a Account with a body incorrect
    Given I fill the request with the data required
      | Phone | 101010 |
    When I create an Account with the name
    Then the status code is a number 400

  @AccountCreation
  Scenario Outline: User creates multiple accounts by specifying at least a status and priority
    Given user specifies account body content
      | Name | <Name> |
      | type | <Type> |
    When user posts to Account endpoint
    Then the status code is a number 201
    And response includes the following
      | success | true |
    And response complies account create 201 schema
    Examples:
      | Name     | Type                        |
      | account1 | Prospect                    |
      | account2 | Customer - Direct           |
      | account3 | Customer - Channel          |
      | account4 | Customer Partner / Reseller |
      | account5 | Installation Partner        |
      | account6 | Installation Partner        |
      | account7 | Technology Partner          |
      | account8 | Other                       |
      | account8 | --None--                    |
