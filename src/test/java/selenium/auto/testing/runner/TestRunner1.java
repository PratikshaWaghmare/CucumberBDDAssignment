package selenium.auto.testing.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:features",
        glue="selenium.auto.testing.stepdefinations", 
        tags="", 
        plugin = {"pretty",
            "html:target/html/htmlreport.html",
            "json:target/json/file.json",
            },
        monochrome=true,
        publish=true,
        dryRun=false
        )

public class TestRunner1 {

}
