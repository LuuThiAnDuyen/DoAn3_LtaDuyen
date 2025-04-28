Feature: Order page
  Scenario: Buy now
    Given user navigates to Order page
    When user enters valid fields and click Payment and delivery
    Then system notice Order successfully and switch to home page