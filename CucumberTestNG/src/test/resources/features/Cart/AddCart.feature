Feature: Cart Page
  @withoutlogin
  Scenario: Add cart successful without login
    Given user navigates to Homepage
    When user clicks an item to show detail product
    And user clicks Add cart and view cart
    Then user verify product be displayed on cart
   @withlogin
  Scenario: Add cart successful with login
    Given user navigates to Home page and login success
    When user clicks an item to show detail product
    And user clicks Add cart and view cart
    Then user verify product be displayed on cart