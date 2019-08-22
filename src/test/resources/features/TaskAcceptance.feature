Feature: Manage Tasks in Salesforce

  Scenario: User creates a task by specifying at least a status and priority
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success | true |
    And task creation response schema is valid

  @UpdateTask
  Scenario: User updates an existing task
    Given user specifies body content
      | Status   | Deferred    |
      | Priority | High        |
    When user patches an existing task
    Then status code is 204

#  Scenario: User updates a task by id and specifying at least a status and priority
#    Given user specifies body content
#      | Status   | Deferred    |
#      | Priority | High        |
#    When user patches Task 00T3i000005aHNREA2
#    Then status code is 204

  @FindTask
  Scenario: A user searches for an existing task
    When user searches for an existing task
    Then status code is 200
#    And response includes the following
#      | Id | 00T3i000005aHNREA2 |

#  Scenario: A user searches for a task by id
#    When user searches for task 00T3i000005aHNREA2
#    Then status code is 200
#    And response includes the following
#      | Id | 00T3i000005aHNREA2 |

  @DeleteTask
  Scenario: A user wants to delete an existing task
    When user makes a delete request for an existing task
    Then status code is 204

#  Scenario: A user wants to delete a specific task
#    When user makes a delete request for task 00T3i000005aHO0EAM
#    Then status code is 204

  Scenario: A user wants to retrieve a summary of Task
    When user makes a get request to endpoint
    Then status code is 200
