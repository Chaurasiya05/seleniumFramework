package seleniumframework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.AbstractComponent.AbstractComponents;

public class LandingPage extends AbstractComponents{
 
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//driver.findElement(By.id("userEmail")).sendKeys("anubhavchaurasiya22@gmail.com");
	@FindBy(id="userEmail")
	WebElement userEmail;
	//driver.findElement(By.id("userPassword")).sendKeys("Anubhav@123");
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	//driver.findElement(By.id("login")).click();
	@FindBy(id="login")
	WebElement submitButton;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	
	public ProductCatalogue loginApplication(String username, String password)
	{
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		submitButton.click();
		return new ProductCatalogue(driver);
	
	}
	public String getErrorMessage()
	{
		return errorMessage.getText();
	}
	
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	
}
