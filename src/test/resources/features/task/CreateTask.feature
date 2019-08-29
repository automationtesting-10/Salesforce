@Task
Feature: Create Tasks in Salesforce

  @CreateTask @Acceptance
  Scenario: User creates a task
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
    When user posts to Task endpoint
    Then the status code is 201
    And the response includes the following
      | success  | true        |
    And the response passes task create 201 schema validation

  @CreateTask @Acceptance
  Scenario: User creates a task and verifies its attributes
    Given a Task have been created with
      | Status   | Not Started |
      | Priority | Low         |
    When user searches for an existing task
    Then the status code is 200
    And the response includes the following
      | Status   | Not Started |
      | Priority | Low         |
    And the response passes task find 200 schema validation

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
    Then the status code is 201
    And the response includes the following
      | success  | true        |
    And the response passes task create 201 schema validation

  @CreateTask @Functional
  Scenario Outline: User creates multiple tasks by specifying at least a status and priority
    Given user specifies body content
      | Status   | <Status>   |
      | Priority | <Priority> |
    When user posts to Task endpoint
    Then the status code is 201
    And the response includes the following
      | success  | true       |
    And the response passes task create 201 schema validation
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
  Scenario Outline: User creates a task and verifies its attributes
    Given a Task have been created with
      | Status   | <Status>   |
      | Priority | <Priority> |
    When user searches for an existing task
    Then the status code is 200
    And the response includes the following
      | Status   | <Status> |
      | Priority | <Priority>         |
    And the response passes task find 200 schema validation
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
    Then the status code is 201
    And the response includes the following
      | success     | true              |
    And the response passes task create 201 schema validation

  @CreateTask @Functional
  Scenario Outline: User creates a Task with a CallType
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | CallType | <Type>      |
    When user posts to Task endpoint
    Then the status code is 201
    And the response includes the following
      | success  | true        |
    And the response passes task create 201 schema validation
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
    Then the status code is 201
    And the response includes the following
      | success  | true        |
    And the response passes task create 201 schema validation

  @CreateTask @Functional
  Scenario Outline: User creates a task by specifying status, priority and a common set of optional fields one at a time
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | <Field>  | <Value>     |
    When user posts to Task endpoint
    Then the status code is 201
    And the response includes the following
      | success  | true        |
    And the response passes task create 201 schema validation
    Examples:
      | Field                 | Value                        |
      | Subject               | AT-10                        |
      | CallType              | Inbound                      |
      | CallObject            | Houston                      |
      | Description           | API-Testing                  |
      | ActivityDate          | 2019-09-26T16:30:00.000+0000 |
      | IsReminderSet         | true                         |
      | IsReminderSet         | false                        |
      | ReminderDateTime      | 2019-11-26T16:30:00.000+0000 |
      | CallDisposition       | Call unsuccessful            |
      | CallDurationInSeconds | 59                           |

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
    Then the status code is 201
    And the response includes the following
      | success  | true        |
    And the response passes task create 201 schema validation
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
    Then the status code is 400
    And the response includes the following
      | errorCode | [INVALID_FIELD]                                   |
      | message   | [No such column 'Equipo' on sobject of type Task] |
    And the response passes task 400 schema validation

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
    Then the status code is 400
    And the response includes the following
      | errorCode | [JSON_PARSER_ERROR] |
    And the response passes task 400 schema validation

  # Bug: This should return code 400 but returns 201 even though the task was not created.
  @Bug
  @CreateTask @Negative
  Scenario: User creates a task by specifying an invalid Status & Priority values
    Given user specifies body content
      | Status   | Daffy  |
      | Priority | Foobar |
    When user posts to Task endpoint
    Then the status code is 400
    And the response passes task 400 schema validation

  @CreateTask @Negative
  Scenario: User creates a task by specifying an invalid CallType value
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | CallType | Wilstermann |
    When user posts to Task endpoint
    Then the status code is 400
    And the response includes the following
      | errorCode | [INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST]                         |
      | message   | [Call Type: bad value for restricted picklist field: Wilstermann] |
    And the response passes task 400 schema validation

  @CreateTask @Negative
  Scenario Outline: User updates a task by specifying an invalid data length
    Given user provides value table with 280 characters
      | Status   | Not Started |
      | Priority | Low         |
      | <Field>  |              |
    When user posts to Task endpoint
    Then the status code is 400
    And the response includes the following
      | errorCode | <errorCode> |
    And the response passes task 400 schema validation
    Examples:
      | Field            | errorCode                                 |
      | Subject          | [STRING_TOO_LONG]                         |
      | CallType         | [INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST] |
      | CallObject       | [STRING_TOO_LONG]                         |
      | CallDisposition  | [STRING_TOO_LONG]                         |
      | ReminderDateTime | [JSON_PARSER_ERROR]                       |
      | ActivityDate     | [JSON_PARSER_ERROR]                       |
      | IsReminderSet    | [JSON_PARSER_ERROR]                       |
