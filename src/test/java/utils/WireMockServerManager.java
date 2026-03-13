package utils;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockServerManager {

    private static WireMockServer wireMockServer;

    public static void start() {

        if (wireMockServer == null) {

            wireMockServer = new WireMockServer(
                    options()
                            .port(8081)
                            .usingFilesUnderClasspath("wiremock")
            );

            wireMockServer.start();

            System.out.println("===================================");
            System.out.println("WireMock started on port 8081");
            System.out.println("===================================");
        }
    }

    public static void stop() {

        if (wireMockServer != null) {

            wireMockServer.stop();

            System.out.println("===================================");
            System.out.println("WireMock stopped");
            System.out.println("===================================");
        }
    }
}