Feature: Delete a Task

  @DeleteTask @Acceptance
  Scenario: A user wants to delete an existing task
  When user makes a delete request for an existing task
  Then status code is 204

  @Negative
  Scenario: A user wants to delete a non-existant Task
    When user makes a delete request for task 00T3i000005aHO0EAM
    Then status code is 404
