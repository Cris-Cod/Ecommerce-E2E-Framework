Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Positive Test of submiting the order
    Given Given I landed on Ecommerce Page
    When Logged in with usrrname <user> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | user            | password |
      | krisx@gmail.com | M12345   |