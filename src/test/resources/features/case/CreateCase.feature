Feature: Creation funtional and acceptance

  Scenario: Create an case with minimun data required
    Given user fills the request case with the data required
      | type | case01 |
    When user creates an case with the type
    Then the status code case is a number 201
    And schema case "case creation schema" is valid
