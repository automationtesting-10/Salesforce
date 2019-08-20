
Feature: Manage Opportunities tests
  Background:
    Given A user log in into the Opportunities page

  Scenario: User creates a new opportunity with the required data
    Given User specifies new body content
      | Name      | test1       |
      | CloseDate | 2019-01-01  |
      | StageName | Qualifier   |
