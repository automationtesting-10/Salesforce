Feature: Manage Tasks in Salesforce

  Background: User is authenticated
    Given a user logs in into the Task page

  Scenario: User creates a task by specifying at least a status and priority
    Given user specifies new body content
      | Status   | Not Started |
      | Priority | Low         |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success | true |

#  Scenario: User updates a task by id and specifying at least a status and priority
#    Given user specifies updated body content
#      | Status   | Deferred    |
#      | Priority | High        |
#    When user patches Task 00T3i000005aHNREA2
#    Then status code is 204

#  Scenario: A user searches for a task by id
#    When user searches for task 00T3i000005aHNREA2
#    Then status code is 200
#    And response includes the following
#      | Id | 00T3i000005aHNREA2 |

#  Scenario: A user wants to delete a task
#    When user makes a delete request for task 00T3i000005aHO0EAM
#    Then status code is 204
