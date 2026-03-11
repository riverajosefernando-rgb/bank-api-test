package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRunner {

    @Test
    void testAll() {

        Results results = Runner.path("classpath:features")
                .tags(System.getProperty("karate.options"))
                .parallel(5);

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}