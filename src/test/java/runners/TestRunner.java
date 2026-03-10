package runners;

import com.intuit.karate.junit5.Karate;

class TestRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:features")
                .tags("@wiremock")   // ejecuta solo escenarios con este tag
                .relativeTo(getClass());
    }
}