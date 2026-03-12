@wiremock
Feature: Account balance

  @balance
  Scenario: Get account balance

    Given url baseUrl
    And path 'balance/123'
    When method get
    Then status 200
    And match response.balance == 1000