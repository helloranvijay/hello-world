package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber cannot run at it's own, To Run you need TestNG Runner or jUnit (Depends on What Assertions You are Using)
@CucumberOptions(features="src/test/java/cucumber",glue="ranvijaybhaskar.stepDefinition", monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
