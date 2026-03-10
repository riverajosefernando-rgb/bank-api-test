Feature: Get authentication token

  Scenario: Generate token

    * def login = call read('../auth/login.feature')

    * def token = login.authToken