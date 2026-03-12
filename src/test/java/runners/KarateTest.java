package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KarateTest {

    @Test
    void testAll() {

        String tags = System.getProperty("karate.options");
        if (tags != null) {
            tags = tags.replace("--tags", "").trim();
        }

        Results results = Runner.path("classpath:features")
                .tags(tags)
                .parallel(5);

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}