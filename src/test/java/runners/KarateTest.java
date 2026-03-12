package runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KarateTest {

    @Test
    void runTests() {

        String tags = System.getProperty("karate.options");

        Runner.Builder runner = Runner.path("classpath:features");

        if (tags != null) {
            tags = tags.replace("@", "").trim();   // 👈 quitar @
            runner = runner.tags(tags);
        }

        Results results = runner.parallel(5);

        System.out.println("Features executed: " + results.getFeaturesTotal());
        System.out.println("Scenarios executed: " + results.getScenariosTotal());

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}