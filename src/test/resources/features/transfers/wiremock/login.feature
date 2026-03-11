@wiremok
Feature: Login API

  @login
  Scenario: Successful login

    Given url baseUrl
    And path 'login'
    And request
"""
{
  "username": "user",
  "password": "pass"
}
"""
    When method post
    Then status 200
    And match response.token == "#string"