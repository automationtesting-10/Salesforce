@Task
Feature: Update Tasks in Salesforce

  @UpdateTask @Acceptance
  Scenario: User updates an existing task
  Given user specifies body content
  | Status   | Deferred    |
  | Priority | High        |
  When user patches an existing task
  Then the status code is 204

  @UpdateTask @Acceptance
  Scenario: User updates a task and verifies its attributes
    Given an existing Task have been updated with
      | Status   | Not Started |
      | Priority | Low         |
    When user searches for an existing task
    Then the status code is 200
    And the response includes the following
      | Status   | Not Started |
      | Priority | Low         |
    And the response passes task find 200 schema validation

  @UpdateTask @Functional
  Scenario Outline: User creates multiple tasks by specifying at least a status and priority
    Given user specifies body content
      | Status   | <Status>   |
      | Priority | <Priority> |
    When user patches an existing task
    Then the status code is 204
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

  @UpdateTask @Functional
  Scenario: User updates a task by specifying description and subject
    Given user specifies body content
      | Description | AT-10 API Testing |
      | Subject     | Salesforce        |
    When user patches an existing task
    Then the status code is 204

  @UpdateTask @Functional
  Scenario Outline: User updates a Task with a CallType
    Given user specifies body content
      | CallType | <Type>      |
    When user patches an existing task
    Then the status code is 204
    Examples:
      | Type     |
      | Inbound  |
      | Outbound |
      | Internal |

  @UpdateTask @Functional
  Scenario Outline: User updates a task and verifies its attributes
    Given an existing Task have been updated with
      | Status   | <Status>   |
      | Priority | <Priority> |
    When user searches for an existing task
    Then the status code is 200
    And the response includes the following
      | Status   | <Status>   |
      | Priority | <Priority> |
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

  @UpdateTask @Functional
  Scenario Outline: User updates a task by specifying status, priority and a common set of optional fields one at a time
    Given user specifies body content
      | <Field>  | <Value>     |
    When user patches an existing task
    Then the status code is 204
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

  @Negative
  Scenario: User updates a task by specifying non-exisiting id
    Given user specifies body content
      | Status   | Deferred    |
      | Priority | High        |
    When user patches Task 00T3i000005aHNREA2
    Then the status code is 404

  @UpdateTask @Negative
  Scenario: User updates a task by specifying invalid key
    Given user specifies body content
      | Equipo    | Wilstermann |
    When user patches an existing task
    Then the status code is 400
    And the response includes the following
      | errorCode | [INVALID_FIELD]                                   |
      | message   | [No such column 'Equipo' on sobject of type Task] |
    And the response passes task 400 schema validation

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
    Then the status code is 400
    And the response includes the following
      | errorCode | [JSON_PARSER_ERROR] |
    And the response passes task 400 schema validation

  # Bug: This should return code 400 but instead returns 204 because these values should be validated but
  #      it turns out that it is not.
  #      But, contrary to the case when creating with invalid Status & Priority values, when updating it
  #      actually allows the insertion of such invalid values.
  @Bug
  @UpdateTask @Negative
  Scenario: User updates a task by specifying an invalid Status & Priority values
    Given user specifies body content
      | Status   | Daffy  |
      | Priority | Foobar |
    When user patches an existing task
    Then the status code is 400

  @UpdateTask @Negative
  Scenario: User updates a task by specifying an invalid CallType value
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | CallType | Wilstermann |
    When user patches an existing task
    Then the status code is 400
    And the response includes the following
      | errorCode | [INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST]                         |
      | message   | [Call Type: bad value for restricted picklist field: Wilstermann] |
    And the response passes task 400 schema validation

    @UpdateTask @Negative
    Scenario Outline: User updates a task by specifying an invalid data length
      Given user provides value list with 280 characters
        | <Field> |
      When user patches an existing task
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
