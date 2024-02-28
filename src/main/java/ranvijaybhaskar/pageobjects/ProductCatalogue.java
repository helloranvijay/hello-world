package ranvijaybhaskar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ranvijaybhaskar.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	String landingPageUrl = "https://rahulshettyacademy.com/client/";
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//PageFactory
	
	@FindBy(css=".mb-3")
	private List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	
	private By productsLists = By.cssSelector(".mb-3");
	private By addToCart = By.xpath("//div[@class='card-body']//*[@class='fa fa-shopping-cart']");
	private By toastMessage = By.cssSelector("#toast-container");
	
	//@FindBy()
	
	//At Real Time Translates to
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsLists);
		this.products = driver.findElements(productsLists);
			return products;
	}
	
	public WebElement getProductByname(String productName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByname(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
}
