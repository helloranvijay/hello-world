package ranvijaybhaskar.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ranvijaybhaskar.TestComponents.BaseTest;
import ranvijaybhaskar.pageobjects.CartPage;
import ranvijaybhaskar.pageobjects.CheckoutPage;
import ranvijaybhaskar.pageobjects.ConfirmationPage;
import ranvijaybhaskar.pageobjects.LandingPage;
import ranvijaybhaskar.pageobjects.ProductCatalogue;

public  class StepDefinition extends BaseTest{
	

	public LandingPage landingPage;
	public ProductCatalogue productCatlogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	
	//Generate Methods via chrome plugin - tidy gerkin
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatlogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to the Cart$")
	public void i_add_to_cart(String product) throws InterruptedException {
		productCatlogue.addProductToCart(product);
		
	}
	
	@When("^Checkout (.+) and submit the Order$")
	public void checkout_submit_order(String productName) {
		cartPage = productCatlogue.goToCart();
		Boolean match = cartPage.verifyDisplayedProduct(productName);
		Assert.assertTrue(match, "The Product "+productName+" was not found in The Cart List");
		
		CheckoutPage checkout = cartPage.goToCheckout();
		checkout.selectCountry("India");
		confirmationPage = checkout.submitOrder();
	}
	
	@Then("{string} Message is displayed on Confirmation Page")
	public void message_displayed_confirmationPage(String string) {
		String msg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase(string), "Confirmation Message doesn't matches");
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String msg) {
		Assert.assertEquals(msg, landingPage.getErrorMessage());
		driver.close();
	}
}