package seleniumframework.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import seleniumframework.AbstractComponent.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".infoWrap h3")
	List<WebElement> cartProducts ;
	@FindBy(css="[style*='list-style'] button")
	WebElement checkoutButton ;
	
	public Boolean verifyProductDisplay(String productName)
	{
		return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
	}
	public CheckoutPage goToCheckout()
	{
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
}
