import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/tests/features",
        glue = "steps")
/**
 * Created by IgorKruchenko on 06.01.2017.
 */
public class StartTest {
}

