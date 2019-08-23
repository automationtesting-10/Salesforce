Feature: Manage Tasks in Salesforce
  Background: User is authenticated
    Given a user sing in the web page SalesForce

@FindContact
Scenario: Find Contact by Id
When User finds an existing Contact by Id
Then the status code is 200
