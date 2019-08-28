Feature: Modify an existing Account

  @UpdateAccount @Acceptance
  Scenario: Update an account sending correct json
    Given an user sets json object for the modify account
      | Name | employee |
    When the user updates existing account by Id
    Then the status code is a number 204

  @UpdateAccount @Functional
  Scenario: Update an account sending correct json
    Given an user sets json object for the modify account
      | Name     | employee         |
      | Type     | Customer         |
      | Phone    | 101010           |
      | Website  | www.customer.com |
      | Industry | customerINC      |
    When the user updates existing account by Id
    Then the status code is a number 204

  @UpdateAccount   @Negative
  Scenario: Update an account with a wrong body
    Given an user sets json object for the modify account
      | Indus | customerINC      |
    When the user updates existing account by Id
    Then the status code is a number 400

  @UpdateAccount @Functional
  Scenario: Update all data of an account with correct json
    Given an user sets json object for the modify account
      | Name              | Account536            |
      | fax               | 323232                |
      | Phone             | 101010                |
      | Jigsaw            | jigsawTest            |
      | SicDesc           | Account536            |
      | Website           | www.customer.com      |
      | Industry          | customerINC           |
      | BillingCity       | Av Customer           |
      | Description       | this is a description |
      | BillingState      | billingState creation |
      | ShippingCity      | cochabamba city       |
      | AccountSource     | Advertisement         |
      | AnnualRevenue     | 4                     |
      | BillingStreet     | billingStreet test    |
      | ShippingState     | california            |
      | BillingCountry    | eeuu                  |
      | ShippingStreet    | america avenue        |
      | ShippingCountry   | eeuu                  |
      | BillingPostalCode | california23          |
    When the user updates existing account by Id
    Then the status code is a number 204
