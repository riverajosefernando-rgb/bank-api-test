package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KarateLocalRunner {

    @Test
    void runLocalTests() {

        Results results = Runner.path("classpath:features")
                .tags("@test") // modify tag to execute specific scenarios
                .parallel(5);

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}