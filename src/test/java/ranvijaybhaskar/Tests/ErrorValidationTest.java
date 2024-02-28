package ranvijaybhaskar.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ranvijaybhaskar.TestComponents.BaseTest;
import ranvijaybhaskar.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest{
	String userName = "frozenfreebee@gmail.com";
	String userWrongName = "frozen@gmail.com";
	String userWrongPassword = "SWrong";
	String errorMsg = "Incorrect email or password.";
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void validateLoginErrorWrongEmail() throws InterruptedException, IOException {
		landingPage.loginApplication(userName, userWrongPassword);
		Assert.assertEquals(errorMsg, landingPage.getErrorMessage());
	}
	@Test(retryAnalyzer= Retry.class)
	public void validateLoginErrorWrongPassword() throws InterruptedException, IOException {
		landingPage.loginApplication(userWrongName, userWrongPassword);
		Assert.assertEquals(errorMsg, landingPage.getErrorMessage());	
	}
}
