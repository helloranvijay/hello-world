package ranvijaybhaskar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ranvijaybhaskar.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	String landingPageUrl = "https://rahulshettyacademy.com/client/";
	
	public LandingPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//PageFactory
	@FindBy(id="userEmail")
	private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement password;
	
	@FindBy(css = "#login")
	private WebElement btnLogin;
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	private By productsLists = By.cssSelector(".mb-3");
	
	@FindBy(css = "[class*='toast-error']")
	private WebElement toastErrorMsg;
	//At Real Time Translates to
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		this.password.sendKeys(password);
		btnLogin.click();
		return new ProductCatalogue(driver);
	}
	
	public void goTo() {
		driver.get(landingPageUrl);
		
	}
	
	public By getProductList() {
		waitForElementToAppear(productsLists);
		return productsLists;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(toastErrorMsg);
		return toastErrorMsg.getText();
	}
}
