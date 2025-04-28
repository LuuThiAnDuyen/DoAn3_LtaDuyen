Feature: Cart page
  Scenario: Delete more products
    Given user navigates to Cart
    When user clicks delete
    Then not find those product in cart