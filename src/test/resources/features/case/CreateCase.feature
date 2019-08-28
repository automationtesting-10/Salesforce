Feature: Creation funtional and acceptance

  @CaseCreation
  Scenario: Create an case with minimun data required
    Given user fills the request case with the data required
      | type | case01 |
    When user creates a case with the type
    Then the status code is 201
    And schema case "case creation schema" is valid

  @CaseCreation @Funtional
  Scenario Outline: User creates multiple cases with type and status data
  by specifying at least a status and priority
    Given user specifies case body content
      | type   | <type>   |
      | Status | <Status> |
    When user posts to case endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes case create 201 schema validation
    Examples:
      | type       | Status    |
      | Mechanical | New       |
      | Electrical | Working   |
      | Electronic | Escalated |
      | Structural | Closed    |
      | Other      | --None--  |

  @CaseCreation @Funtional
  Scenario Outline: User creates multiple cases with Priority and status data
  by specifying at least a status and priority
    Given user specifies case body content
      | Priority | <Priority> |
      | Status   | <Status>   |
    When user posts to case endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes case create 201 schema validation
    Examples:
      | Priority | Status    |
      | Medium   | New       |
      | Medium   | Working   |
      | Low      | Escalated |
      | High     | Closed    |
      | --None-- | --None--  |

  @CaseCreation @Funtional
  Scenario Outline: User creates multiple cases with case origin and status data
  by specifying at least a status and priority
    Given user specifies case body content
      | Origin | <Origin> |
      | Status | <Status> |
    When user posts to case endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes case create 201 schema validation
    Examples:
      | Origin   | Status    |
      | Phone    | New       |
      | Email    | Working   |
      | Web      | Escalated |
      | Web      | Closed    |
      | --None-- | --None--  |

  @CaseCreation @Funtional
  Scenario Outline: User creates multiple cases with case reason and status data
  by specifying at least a status and priority
    Given user specifies case body content
      | Reason | <Reason> |
    When user posts to case endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes case create 201 schema validation
    Examples:
      | Reason               |
      | Installation         |
      | Equipment Complexity |
      | Performance          |
      | Breakdown            |
      | Equipment Design     |
      | Feedback             |
      | Other                |
      | --None--             |

  @CaseCreation @Funtional
  Scenario Outline: User creates multiple cases with four case fields and status data
  by specifying at least a status and priority
    Given user specifies case body content
      | Reason   | <Reason>   |
      | Origin   | <Origin>   |
      | Status   | <Status>   |
      | Priority | <Priority> |
    When user posts to case endpoint
    Then the status code is 201
    And the response includes the following
      | success | true |
    And the response passes case create 201 schema validation
    Examples:
      | Reason               | Origin    | Status    | Priority |
      | Installation         | Phone     | New       | Medium   |
      | Equipment Complexity | Email     | New       | Medium   |
      | Performance          | Web       | Escalated | High     |
      | Breakdown            | Web       | Working   | Low      |
      | Equipment Design     | Web       | Escalated | Low      |
      | Feedback             | --None--  | --None--  | --None-- |
      | Other                | Phone     | Escalated | High     |
      | --None--             | Phone     | Escalated | High     |
    