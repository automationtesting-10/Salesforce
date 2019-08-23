Feature: Update Tasks in Salesforce

  @UpdateTask @Acceptance
  Scenario: User updates an existing task
  Given user specifies body content
  | Status   | Deferred    |
  | Priority | High        |
  When user patches an existing task
  Then status code is 204

  @Negative
  Scenario: User updates a task by specifying non-exisiting id and at least a status and priority
    Given user specifies body content
      | Status   | Deferred    |
      | Priority | High        |
    When user patches Task 00T3i000005aHNREA2
    Then status code is 404

#  @UpdateTasks @Functional
#  Scenario Outline: User updates sequentially multiple tasks by specifying at least a status and priority
#    Given user specifies <Status> and <Priority>
#    When user patches an existing task
#    Then the status code is 204
#    Examples:
#      | Status                  | Priority |
#      | Not Started             | Low      |
#      | Deferred                | Normal   |
#      | Waiting on someone else | High     |
#      | Completed               | Normal   |
#      | In Progress             | Low      |


  @UpdateTask @Functional
  Scenario: User updates a task by specifying status, priority and description
    Given user specifies body content
      | Status      | Not Started |
      | Priority    | Low         |
      | Description | AT-10       |
    When user patches an existing task
    Then status code is 204

  @UpdateTask @Negative
  Scenario: User updates a task by specifying invalid key
    Given user specifies body content
      | Equipo    | Wilstermann |
    When user patches an existing task
    Then status code is 400
    And response includes the following
      | errorCode | [INVALID_FIELD]                                   |
      | message   | [No such column 'Equipo' on sobject of type Task] |

  @UpdateTask @Negative
  Scenario: User updates a task by specifying malformed json content
    Given user specifies json content
    """
    {
      "Status""Deferred"
      "Priority""Low"
    }
    """
    When user patches an existing task
    Then status code is 400
    And response includes the following
      | errorCode | [JSON_PARSER_ERROR] |
