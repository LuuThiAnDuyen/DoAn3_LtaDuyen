Feature: Sign up page

  @success
  Scenario: Sign up success
    Given user navigates to Sign up page
    When user enters complete and valid information into the required fields
    And click sign up button
    Then system notice sign up success and direct to login page

  @empty
  Scenario Outline: Sign Up empty
    Given user navigates to Sign up page
    When user left one of the information fields blank "<username>" or "<name>" or "<telephone>" or "<email>" or "<pass1>" or "<pass2>"
    And click sign up button
    Then system notice error message and do not turn page
    Examples:
      | username | name  | telephone  | email             | pass1  | pass2  |
      |          | duyên | 0326541203 | anduyen@gmail.com | 247204 | 247204 |
      | anduyen5 |       | 0326541203 | anduyen@gmail.com | 247204 | 247204 |
      | anduyen5 | duyên |            | anduyen@gmail.com | 247204 | 247204 |
      | anduyen5 | duyên | 0326541203 |                   | 247204 | 247204 |
      | anduyen5 | duyên | 0326541203 | anduyen@gmail.com |        | 247204 |
      | anduyen5 | duyên | 0326541203 | anduyen@gmail.com | 247204 |        |
      |          |       |            |                   |        |        |
