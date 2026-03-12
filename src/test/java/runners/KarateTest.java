package runners;

import com.intuit.karate.junit5.Karate;

class KarateTest {

    @Karate.Test
    Karate runTests() {
        return Karate.run("classpath:features");
    }

}