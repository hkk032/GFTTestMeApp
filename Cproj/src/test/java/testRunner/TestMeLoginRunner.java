package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions (
		features = "features\\TestMeLogin.feature",
		glue = {"stepDefinition"},
		plugin = {
				"json:target/cucumber-report/TestMeTest.json"
		}
		)

public class TestMeLoginRunner {

}
