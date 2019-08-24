Feature: Create Tasks in Salesforce

  @CreateTask @Acceptance
  Scenario: User creates a task by specifying at least a status and priority
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success | true |
    And response complies task schema

  @CreateTasks @Functional
  Scenario Outline: User creates multiple tasks by specifying at least a status and priority
    Given user specifies <Status> and <Priority>
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success | true |
    And response complies task schema
    Examples:
      | Status                  | Priority |
      | Not Started             | Low      |
      | Deferred                | Normal   |
      | Waiting on someone else | High     |
      | Completed               | Normal   |
      | In Progress             | Low      |

  @CreateTask @Functional
  Scenario: User creates a task by specifying status, priority and description
    Given user specifies body content
      | Status      | Not Started |
      | Priority    | Low         |
      | Description | AT-10       |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success | true |
    And response complies task schema

  @CreateTask @Negative
  Scenario: User creates a task by specifying invalid key
    Given user specifies body content
      | Equipo    | Wilstermann |
    When user posts to Task endpoint
    Then status code is 400
    And response includes the following
      | errorCode | [INVALID_FIELD]                                   |
      | message   | [No such column 'Equipo' on sobject of type Task] |

  @CreateTask @Negative
  Scenario: User creates a task by specifying malformed json content
    Given user specifies json content
    """
    {
      "Status""Deferred"
      "Priority""Low"
    }
    """
    When user posts to Task endpoint
    Then status code is 400
    And response includes the following
      | errorCode | [JSON_PARSER_ERROR] |
