Feature: Transfer with insufficient balance

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

    * def auth = call read('../auth/getToken.feature')
    * def token = auth.token

    * url baseUrl
    * header Authorization = 'Bearer ' + token
  @test1
  Scenario: Transfer fails due to insufficient funds

    * def fromAccount = "1001"
    * def toAccount = "2001"
    * def amount = 99999

    Given path "/transfers"

    And request read('classpath:requests/transferRequest.json')


    When method POST

    Then status 400

    And match response.error == "INSUFFICIENT_FUNDS"