package cucumber;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features="./src/test/java/features",
		glue="stepDefinitions", 
		monochrome = true,
		publish = true
		)

public class TestRunner extends AbstractTestNGCucumberTests{
	
}

