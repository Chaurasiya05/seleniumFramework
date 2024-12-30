package seleniumframework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.AbstractComponent.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	WebDriver driver;
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="h1[style*='text-transform']")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		String Message= confirmationMessage.getText();
		return Message;
	}
	
}
