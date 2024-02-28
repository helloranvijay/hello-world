package ranvijaybhaskar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ranvijaybhaskar.abstractcomponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//PageFactory

	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	@FindBy(css=".totalRow button")
	private WebElement btnCheckout;
	
	
	private By productList = By.xpath("//tr[@class='ng-star-inserted']/td[2]");
	
	//@FindBy()
	
	//At Real Time Translates to
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	
	public boolean verifyOrderedProduct(String productName) {
		waitForElementToAppear(productList);
	List <WebElement> orderedProducts = driver.findElements(productList);
	Boolean match = orderedProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public CheckoutPage goToCheckout() {
		btnCheckout.click();
		return new CheckoutPage(driver);
	}

}
