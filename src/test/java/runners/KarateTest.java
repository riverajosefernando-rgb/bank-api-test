package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KarateTest {

    @Test
    void testAll() {

        String karateOptions = System.getProperty("karate.options");

        Runner.Builder runner = Runner.path("classpath:features");

        if (karateOptions != null && karateOptions.contains("--tags")) {
            String tag = karateOptions.replace("--tags", "").trim();
            runner = runner.tags(tag);
        }

        Results results = runner.parallel(5);

        System.out.println("Scenarios executed: " + results.getScenariosTotal());

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}