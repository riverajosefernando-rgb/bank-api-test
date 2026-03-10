Feature: Transfer to invalid account

  Background:

    * def auth = call read('../auth/getToken.feature')
    * def token = auth.token

    * url baseUrl
    * header Authorization = 'Bearer ' + token

  @test
  Scenario: Invalid destination account

    * def fromAccount = "1001"
    * def toAccount = "3001"
    * def amount = 100

    Given path "/transfers"

    And request read('classpath:requests/transferRequest.json')

    When method POST

    Then status 404

    And match response.error == "ACCOUNT_NOT_FOUND"