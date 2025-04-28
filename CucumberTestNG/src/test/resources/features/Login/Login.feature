Feature: Login Page
  @success
  Scenario: Login success
    Given user navigate to login page
    When user enter username and password
    And click login button
    Then user redirect to home page

  @fail
  Scenario Outline: Login fail
    Given user navigate to login page
    When user enter username invalid "<userInvalid>" or password invalid "<passInvalid>" and click login button
    Then displays an error message and do not turn page
    Examples:
      | userInvalid | passInvalid |
      |             | 247204      |
      | anduyen     |             |
      | ltaduyen    | 247204      |
      | anduyen     | 123456      |





