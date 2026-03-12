package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KarateTest {

    @Test
    void testAll() {

        String tag = System.getProperty("karate.options");

        if (tag != null && tag.contains("--tags")) {
            tag = tag.replace("--tags", "").trim();
        }

        Results results = Runner.path("classpath:features")
                .tags(tag)
                .parallel(5);

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}