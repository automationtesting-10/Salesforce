Feature: Creation funtional and acceptance

  Scenario: Create an case with minimun data required
    Given I fill the request case with the data required
      | type | case01 |
    When I create an case with the name
    Then the status code case is a number 201
    And schema case "case creation schema" is valid
