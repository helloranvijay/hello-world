package ranvijaybhaskar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ranvijaybhaskar.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//PageFactory
	
	@FindBy(css=".cartSection h3")
	private WebElement cartProduct;
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	@FindBy(css=".totalRow button")
	private WebElement btnCheckout;
	
	
	By allCartProducts = By.cssSelector(".cartSection h3");
	By addToCart = By.xpath("//div[@class='card-body']//*[@class='fa fa-shopping-cart']");
	By toastMessage = By.cssSelector("#toast-container");
	
	//@FindBy()
	
	//At Real Time Translates to
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	
	public boolean verifyDisplayedProduct(String productName) {
		waitForElementToAppear(allCartProducts);
	List <WebElement> cartProducts = driver.findElements(allCartProducts);
	Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public CheckoutPage goToCheckout() {
		btnCheckout.click();
		return new CheckoutPage(driver);
	}

}
