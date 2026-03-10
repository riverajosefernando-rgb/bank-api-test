Feature: Bank Transfer API

  Background:
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

    * def auth = call read('../auth/getToken.feature')
    * def token = auth.token

    * url baseUrl
    * header Authorization = 'Bearer ' + token
    * header Content-Type = 'application/json'

  @test
  Scenario Outline: Transfer validations

    * def fromAccount = "<from>"
    * def toAccount = "<to>"
    * def amount = <amount>

    Given path "/transfers"
    And request read('classpath:requests/transferRequest.json')

    When method POST

    Then status <status>

    * if ("<result>" != "") karate.match(response.status, "<result>")
    * if ("<error>" != "") karate.match(response.error, "<error>")


    Examples:
      | from | to   | amount | status | result  | error              |
      | 1001 | 2001 | 200    | 201    | SUCCESS |                    |
      | 1001 | 2001 | 999999 | 400    |         | INSUFFICIENT_FUNDS |
      | 1001 | 3001 | 100    | 404    |         | ACCOUNT_NOT_FOUND  |
