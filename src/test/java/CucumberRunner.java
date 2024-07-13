import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features/Login.feature"}
)

public class CucumberRunner extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner TestNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        TestNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @DataProvider
    public Object[][] features() {
        return TestNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        TestNGCucumberRunner.finish();
    }
}
