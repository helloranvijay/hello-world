package ranvijaybhaskar.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ranvijaybhaskar.TestComponents.BaseTest;
import ranvijaybhaskar.pageobjects.CartPage;
import ranvijaybhaskar.pageobjects.CheckoutPage;
import ranvijaybhaskar.pageobjects.ConfirmationPage;
import ranvijaybhaskar.pageobjects.OrdersPage;
import ranvijaybhaskar.pageobjects.ProductCatalogue;

public class EcommerceOrderTest extends BaseTest{
	String userName = "frozenfreebee@gmail.com";
	String userPassword = "Sourav@18";
	String productName = "ZARA COAT 3";
	String country = "India";
	String confirmationMsg = "THANKYOU FOR THE ORDER.";
	
	@Test(dataProvider = "getData", groups = {"Purchase","Smoke"})
	public void submitOrderTest(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatalogue productCatlogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatlogue.addProductToCart(input.get("product"));
		CartPage cartPage =productCatlogue.goToCart();
		
		 
		Boolean match = cartPage.verifyDisplayedProduct(input.get("product"));
		Assert.assertTrue(match, "The Product "+input.get("product")+" was not found in The Cart List");
		
		CheckoutPage checkout = cartPage.goToCheckout();
		checkout.selectCountry(country);
		ConfirmationPage confirmationPage = checkout.submitOrder();
		String msg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(msg.equalsIgnoreCase(confirmationMsg), "Confirmation Message doesn't matches");
	}
	
	@Test(dependsOnMethods = {"submitOrderTest"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatlogue = landingPage.loginApplication(userName, userPassword);
		OrdersPage orderPage = productCatlogue.goToOrder();
		Boolean orderFound = orderPage.verifyOrderedProduct(productName);
		Assert.assertTrue(orderFound);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//Use Hash Maps for Better Work
		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * userName); map.put("password", userPassword); map.put("product",productName);
		 */
		List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir")+"//src//test//java//ranvijaybhaskar//data//PurchaseOrder.json");
	
		return new Object[][] {{data.get(0)}};
	}
	
	//Call This Method When Test Case Fails

	//Extent Reports
}
