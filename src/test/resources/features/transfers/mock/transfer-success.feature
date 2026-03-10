Feature: Bank Transfer API

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

    * def auth = call read('../auth/getToken.feature')
    * def token = auth.token

    * url baseUrl
    * header Authorization = 'Bearer ' + token
    * header Content-Type = 'application/json'

  Scenario: Successful transfer

    * def fromAccount = "1001"
    * def toAccount = "2001"
    * def amount = 200

    Given path "/transfers"

    And request read('classpath:requests/transferRequest.json')

    When method POST

    Then status 201

    And match response.status == "SUCCESS"
    And match response.transactionId != null