Feature: Manage Tasks in Salesforce
  Background: User is authenticated
    Given a user sing in the web page SalesForce

@Acceptance
Scenario: Update a Contact
Given User sets the lastName
  | lastName   |  testName   |
When User updates existing contact by Id
Then the status code is 204

@Negative
Scenario: Update a contact by Id that does not exist
Given User sets lastName to the contact
  | lastName   |  testName   |
When User updates contact by Id 00f12s2a11423874
Then The status code is 404

@Negative
Scenario: Update a contact with an incorrect json
Given User provides the following json
"""
    {
      "LastName","TestName"
    }
    """
When User updates existing contact by Id
Then The status code is 400
