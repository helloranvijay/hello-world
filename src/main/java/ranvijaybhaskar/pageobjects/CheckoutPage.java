package ranvijaybhaskar.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ranvijaybhaskar.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//PageFactory

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(css = ".action__submit")
	private WebElement submit;
	
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	private WebElement selectCountry;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String selectedCountry) {
		Actions a = new Actions(driver);
		a.sendKeys(country, selectedCountry).build().perform();
		
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
