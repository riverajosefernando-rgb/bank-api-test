package runners;

import com.intuit.karate.core.MockServer;

public class MockServerRunner {

    public static void main(String[] args) throws InterruptedException {

        MockServer server = MockServer.feature("classpath:mock/bank-mock.feature")
                .http(8080)
                .build();

        System.out.println("Mock server running on port: " + server.getPort());

        // mantiene el servidor vivo
//        Thread.currentThread().join();
    }
}