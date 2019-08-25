Feature: Create Tasks in Salesforce

  @CreateTask @Acceptance
  Scenario: User creates a task
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success  | true        |
    And response complies task create 201 schema

  @CreateTask @Acceptance
  Scenario: User creates a series of scheduled tasks
    Given user specifies body content
      | Status                  | Not Started                  |
      | Priority                | Low                          |
      | IsRecurrence            | true                         |
      | RecurrenceDayOfWeekMask | 4                            |
      | RecurrenceInstance      | First                        |
      | RecurrenceInterval      | 1                            |
      | RecurrenceType          | RecursMonthlyNth             |
      | RecurrenceStartDateOnly | 2019-08-26T16:30:00.000+0000 |
      | RecurrenceEndDateOnly   | 2019-12-26T16:30:00.000+0000 |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success  | true        |
    And response complies task create 201 schema

  @CreateTasks @Functional
  Scenario Outline: User creates multiple tasks by specifying at least a status and priority
    Given user specifies body content
      | Status   | <Status>   |
      | Priority | <Priority> |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success  | true       |
    And response complies task create 201 schema
    Examples:
      | Status                   | Priority |
      | Deferred                 | Low      |
      | Deferred                 | Normal   |
      | Deferred                 | High     |
      | Waiting on someone else  | Low      |
      | Waiting on someone else  | Normal   |
      | Waiting on someone else  | High     |
      | Not Started              | Low      |
      | Not Started              | Normal   |
      | Not Started              | High     |
      | In Progress              | Low      |
      | In Progress              | Normal   |
      | In Progress              | High     |
      | Completed                | Low      |
      | Completed                | Normal   |
      | Completed                | High     |

  @CreateTask @Functional
  Scenario: User creates a task by specifying status, priority and description
    Given user specifies body content
      | Status      | Not Started       |
      | Priority    | Low               |
      | Description | AT-10 API Testing |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success     | true              |
    And response complies task create 201 schema

  @CreateTasks @Functional
  Scenario Outline: User creates a Task with a CallType
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | CallType | <Type>      |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success  | true        |
    And response complies task create 201 schema
    Examples:
      | Type     |
      | Inbound  |
      | Outbound |
      | Internal |

  @CreateTask @Functional
  Scenario: User creates a task by specifying status, priority and subject
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | Subject  | Salesforce  |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success  | true        |
    And response complies task create 201 schema

  @CreateTask @Functional
  Scenario Outline: User creates a series of value-per-week scheduled tasks by specifying a RecurrenceDayOfWeekMask
    Given user specifies body content
      | Status                  | Not Started                  |
      | Priority                | Low                          |
      | IsRecurrence            | true                         |
      | RecurrenceDayOfWeekMask | <Values>                     |
      | RecurrenceInterval      | 1                            |
      | RecurrenceType          | RecursWeekly                 |
      | RecurrenceStartDateOnly | 2019-08-26T16:30:00.000+0000 |
      | RecurrenceEndDateOnly   | 2019-12-26T16:30:00.000+0000 |
    When user posts to Task endpoint
    Then status code is 201
    And response includes the following
      | success  | true        |
    And response complies task create 201 schema
    Examples:
      | Values |
      |      1 |
      |      2 |
      |      4 |
      |      8 |
      |      9 |
      |     10 |
      |     12 |
      |     16 |
      |     24 |
      |     32 |
      |     40 |
      |     64 |
      |     72 |

  @CreateTask @Negative
  Scenario: User creates a task by specifying invalid key
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | Equipo   | Wilstermann |
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

  # Bug: This should return code 400 but returns 201 even though the task was not created.
  @CreateTask @Negative
  Scenario: User creates a task by specifying an invalid Status & Priority values
    Given user specifies body content
      | Status   | Daffy  |
      | Priority | Foobar |
    When user posts to Task endpoint
    Then status code is 201

  @CreateTask @Negative
  Scenario: User creates a task by specifying an invalid CallType value
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | CallType | Wilstermann |
    When user posts to Task endpoint
    Then status code is 400
    And response includes the following
      | errorCode | [INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST]                         |
      | message   | [Call Type: bad value for restricted picklist field: Wilstermann] |
