Feature: Creation funtional and acceptance

  @CreateAccount @Acceptance
  Scenario: Create an Account with minimun data required
    Given user fills the request with the data required
      | Name | Account536 |
    When user creates an Account with the name
    Then the status code is 201
    And schema "account creation schema" is valid

  @CreateAccount @Funtional
  Scenario: Create an Account with five data
    Given user fills the request with the data required
      | Name     | Account536       |
      | Type     | Customer         |
      | Phone    | 101010           |
      | Website  | www.customer.com |
      | Industry | customerINC      |
    When user creates an Account with the name
    Then the status code is 201
    And schema "account creation schema" is valid

  @Negative
  Scenario: Create a Account with a body incorrect
    Given user fills the request with the data required
      | Phone | 101010 |
    When user creates an Account with the name
    Then the status code is 400

  @CreateAccount @Funtional
  Scenario Outline: User creates multiple accounts with type data
  by specifying at least a status and priority
    Given user specifies account body content
      | Name | <Name> |
      | type | <Type> |
    When user posts to Account endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes account create 201 schema validation
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

  @CreateAccount @Funtional
  Scenario Outline: User creates multiple accounts with industry data
  specific  at least a status and priority
    Given user specifies account body content
      | Name     | <Name>     |
      | Industry | <Industry> |
    When user posts to Account endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes account create 201 schema validation
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

  @CreateAccount @Funtional
  Scenario Outline: User creates multiple accounts with rating data
  by specifying at least a status and priority
    Given user specifies account body content
      | Name   | <Name>   |
      | Rating | <Rating> |
    When user posts to Account endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes account create 201 schema validation
    Examples:
      | Name     | Rating   |
      | account1 | Hot      |
      | account2 | Warm     |
      | account3 | Cold     |
      | account4 | --None-- |

  @CreateAccount @Funtional
  Scenario Outline: User creates multiple accounts with ownership data
  by specifying at least a status and priority
    Given user specifies account body content
      | Name      | <Name>      |
      | Ownership | <Ownership> |
    When user posts to Account endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes account create 201 schema validation
    Examples:
      | Name     | Ownership  |
      | account1 | Public     |
      | account2 | Private    |
      | account3 | Subsidiary |
      | account4 | Other      |
      | account5 | --None--   |

  @CreateAccount @Funtional
  Scenario: Create an Account with all possible data
    Given user fills the request with the data required
      | Name              | Account536            |
      | fax               | 323232                |
      | Phone             | 101010                |
      | Jigsaw            | jigsawTest            |
      | SicDesc           | Account536            |
      | Website           | www.customer.com      |
      | Industry          | customerINC           |
      | BillingCity       | Av Customer           |
      | Description       | this is a description |
      | BillingState      | billingState creation |
      | ShippingCity      | cochabamba city       |
      | AccountSource     | Advertisement         |
      | AnnualRevenue     | 4                     |
      | BillingStreet     | billingStreet test    |
      | ShippingState     | california            |
      | BillingCountry    | eeuu                  |
      | ShippingStreet    | america avenue        |
      | ShippingCountry   | eeuu                  |
      | BillingPostalCode | california23          |
    When user creates an Account with the name
    Then the status code is 201
    And schema "account creation schema" is valid
