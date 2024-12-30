package seleniumframework.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import seleniumframework.AbstractComponent.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts ;
	
	public boolean verifyOrderDisplay(String orderName)
	{
		Boolean match= orderProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(orderName));
		return match;
	}

	
	

	
}
