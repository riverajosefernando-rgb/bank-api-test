function fn() {

  var config = {};

 // Mock local
//  config.baseUrl = "http://localhost:8080";

  // Wiremock
  config.baseUrl = "http://localhost:8081";

  return config;
}