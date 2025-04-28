Feature: Searching

  @click
  Scenario: Search by suggestion
    Given user navigates to Home page
    When user clicks on suggestion under search box
    Then system returns appropriate results

  @keyword
  Scenario Outline: Search by keyword
    Given user navigates to Home page
    When user enters search keyword "<SearchKeyword>" and clicks enter
    Then system returns appropriate results
    Examples:
      | SearchKeyword |
      | SỮA RỬA MẶT   |
      | SUA RUA MAT   |
      | SỮA           |
      | RỬA           |
      | MẶT           |
      | S             |
      | M             |
      | Ă             |