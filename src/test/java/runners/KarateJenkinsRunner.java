package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KarateJenkinsRunner {

    private static final Logger logger = LoggerFactory.getLogger(KarateJenkinsRunner.class);

    @Test
    void runTests() {

        String tags = System.getProperty("karate.tags");

        Runner.Builder runner = Runner.path("classpath:features");

        if (tags != null && !tags.isEmpty()) {
            runner.tags(tags.split(","));
        }

        Results results = runner.parallel(5);

        logger.info("===================================");
        logger.info("Tags              : {}", tags);
        logger.info("Features executed : {}", results.getFeaturesTotal());
        logger.info("Scenarios executed: {}", results.getScenariosTotal());
        logger.info("Failures          : {}", results.getFailCount());
        logger.info("===================================");

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}