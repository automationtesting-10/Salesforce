Feature: Update Tasks in Salesforce

  @UpdateTask @Acceptance
  Scenario: User updates an existing task
  Given user specifies body content
  | Status   | Deferred    |
  | Priority | High        |
  When user patches an existing task
  Then status code is 204

  @UpdateTask @Functional
  Scenario Outline: User creates multiple tasks by specifying at least a status and priority
    Given user specifies body content
      | Status   | <Status>   |
      | Priority | <Priority> |
    When user patches an existing task
    Then status code is 204
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
    Then status code is 204

  @UpdateTask @Functional
  Scenario Outline: User updates a Task with a CallType
    Given user specifies body content
      | CallType | <Type>      |
    When user patches an existing task
    Then status code is 204
    Examples:
      | Type     |
      | Inbound  |
      | Outbound |
      | Internal |

  @Negative
  Scenario: User updates a task by specifying non-exisiting id
    Given user specifies body content
      | Status   | Deferred    |
      | Priority | High        |
    When user patches Task 00T3i000005aHNREA2
    Then status code is 404

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

  # Bug: This should return code 400 but instead returns 204 because these values should be validated but
  #      it turns it isn't.
  #      But, contrary to the case when creating with invalid Status & Priority values, when updating it
  #      actually allows the insertion of such invalid values.
  @UpdateTask @Negative
  Scenario: User updates a task by specifying an invalid Status & Priority values
    Given user specifies body content
      | Status   | Daffy  |
      | Priority | Foobar |
    When user patches an existing task
    Then status code is 204

  @UpdateTask @Negative
  Scenario: User updates a task by specifying an invalid CallType value
    Given user specifies body content
      | Status   | Not Started |
      | Priority | Low         |
      | CallType | Wilstermann |
    When user patches an existing task
    Then status code is 400
    And response includes the following
      | errorCode | [INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST]                         |
      | message   | [Call Type: bad value for restricted picklist field: Wilstermann] |
