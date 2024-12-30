package seleniumframework.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumframework.PageObject.CartPage;
import seleniumframework.PageObject.OrderPage;

public class AbstractComponents {

	
	WebDriver driver;
	public AbstractComponents(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	public void waitForElementToBeAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToBeDisappear() throws InterruptedException
	{

		Thread.sleep(2000);
		//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
//		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCart()
	{
		cartHeader.click();
		return new CartPage(driver);
	}
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		return new OrderPage(driver);
	}
	
	
	
}
