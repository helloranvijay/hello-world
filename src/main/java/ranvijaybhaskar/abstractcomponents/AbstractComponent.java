package ranvijaybhaskar.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ranvijaybhaskar.pageobjects.CartPage;
import ranvijaybhaskar.pageobjects.OrdersPage;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait; 
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orders;
	

	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.invisibilityOf(ele));	
	}

	public CartPage goToCart() {
		cart.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrder() {
		orders.click();
		return new OrdersPage(driver);
	}
}
