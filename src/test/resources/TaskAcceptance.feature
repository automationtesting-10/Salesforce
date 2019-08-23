Feature: Manage Tasks in Salesforce

  Background: User is authenticated
    Given a user logs in into the Task page

  Scenario: User creates a task by specifying at least a status and priority
    Given user specifies new body content
      | Status   | Not Started |
      | Priority | Low         |
    When user posts to Task endpoint
    Then the status code is 201 after creating
    And creation response includes the following
      | success | true |

#  Scenario: User updates a task by id and specifying at least a status and priority
#    Given user specifies updated body content
#      | Status   | Not Started |
#      | Priority | Low         |
#    When user patches Task 00T3i000005ZrUDEA0
#    Then the status code is 204 after updating
#
#  Scenario: A user searches for a task by id
#    When user searches for task 00T3i000005ZrUDEA0
#    Then status code is 200 after finding
#    And search response contains the following
#      | Id | 00T3i000005ZrUDEA0 |
#
#  Scenario: A user wants to delete a task
#    When user makes a delete request for task 00T3i000005ZrUDEA0
#    Then status code is 200 after deleting
