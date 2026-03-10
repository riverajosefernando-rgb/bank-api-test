Feature: Authentication API

  Background:

    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

  Scenario: Login and get token

    Given url baseUrl
    And path "/auth/login"

# leer archivo de credenciales
  * def credentials = read('classpath:config/credentials.txt')

# convertir texto a variables
  * def username = credentials.split('\n')[0].split('=')[1].trim()
  * def password = credentials.split('\n')[1].split('=')[1].trim()

  * def loginRequest = read('classpath:data/auth/loginRequest.json')
    And request loginRequest

    When method POST

    Then status 200

    And match response.token != null

    * def authToken = response.token