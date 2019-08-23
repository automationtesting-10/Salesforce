Feature: Creation funtional and acceptance

  Background:
    Given I log in with Authorization token

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

  Scenario: Create a Account with a body incorrect
    Given I fill the request with the data required
      | Phone | 101010 |
    When I create an Account with the name
    Then the status code is a number 400
