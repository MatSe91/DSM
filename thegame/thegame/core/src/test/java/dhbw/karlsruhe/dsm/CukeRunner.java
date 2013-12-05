package dhbw.karlsruhe.dsm;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@Cucumber.Options(
		format = { "pretty", "html:target/cucumber" },
		features = { "src/test/resources" }
		)
public class CukeRunner {
}
