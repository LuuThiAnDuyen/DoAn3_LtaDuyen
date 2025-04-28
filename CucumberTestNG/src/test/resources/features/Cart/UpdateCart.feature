Feature: Cart page
  Scenario: Update cart
    Given user navigates to cart page
    When user change quantity of product
    Then system returns respective amount
  Scenario: Update by entering quantity
    Given user navigates to cart page
    When user enters quantity of product
    Then system returns respective amount