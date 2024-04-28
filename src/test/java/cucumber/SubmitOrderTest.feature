Feature: Purchase the order from Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submiting the order
    Given Logged in with usrrname <user> and password <password>
    When I add product <productName> from cart
    And checkout <productName> and submit ther order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples:
      | user            | password  | productName     |
      | krisx@gmail.com | M123456#n | ADIDAS ORIGINAL |