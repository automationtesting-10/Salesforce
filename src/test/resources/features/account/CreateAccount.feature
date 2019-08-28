Feature: Creation funtional and acceptance

  @AccountCreation
  Scenario: Create an Account with minimun data required
    Given user fills the request with the data required
      | Name | Account536 |
    When I create an Account with the name
    Then the status code is a number 201
    And schema "account creation schema" is valid

  @AccountCreation
  Scenario: Create an Account with five data
    Given user fills the request with the data required
      | Name     | Account536       |
      | Type     | Customer         |
      | Phone    | 101010           |
      | Website  | www.customer.com |
      | Industry | customerINC      |
    When I create an Account with the name
    Then the status code is a number 201
    And schema "account creation schema" is valid

  Scenario: Create a Account with a body incorrect
    Given user fills the request with the data required
      | Phone | 101010 |
    When I create an Account with the name
    Then the status code is a number 400

  @AccountCreation
  Scenario Outline: User creates multiple accounts with type data
  by specifying at least a status and priority
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

  @AccountCreation
  Scenario Outline: User creates multiple accounts with industry data
  specific  at least a status and priority
    Given user specifies account body content
      | Name     | <Name>     |
      | Industry | <Industry> |
    When user posts to Account endpoint
    Then the status code is a number 201
    And response includes the following
      | success | true |
    And response complies account create 201 schema
    Examples:
      | Name      | Industry           |
      | account1  | Agriculture        |
      | account2  | Apparel            |
      | account3  | Banking            |
      | account4  | Biothechnology     |
      | account5  | Food & Beverage    |
      | account6  | Healthcare         |
      | account7  | Hospitality        |
      | account8  | Insurance          |
      | account9  | Retail             |
      | account10 | Shipping           |
      | account11 | Technology         |
      | account12 | Telecommunications |
      | account13 | --None--           |

  @AccountCreation
  Scenario Outline: User creates multiple accounts with rating data
  by specifying at least a status and priority
    Given user specifies account body content
      | Name   | <Name>   |
      | Rating | <Rating> |
    When user posts to Account endpoint
    Then the status code is a number 201
    And response includes the following
      | success | true |
    And response complies account create 201 schema
    Examples:
      | Name     | Rating   |
      | account1 | Hot      |
      | account2 | Warm     |
      | account3 | Cold     |
      | account4 | --None-- |

  @AccountCreation
  Scenario Outline: User creates multiple accounts with ownership data
  by specifying at least a status and priority
    Given user specifies account body content
      | Name      | <Name>      |
      | Ownership | <Ownership> |
    When user posts to Account endpoint
    Then the status code is a number 201
    And response includes the following
      | success | true |
    And response complies account create 201 schema
    Examples:
      | Name     | Ownership  |
      | account1 | Public     |
      | account2 | Private    |
      | account3 | Subsidiary |
      | account4 | Other      |
      | account5 | --None--   |
    