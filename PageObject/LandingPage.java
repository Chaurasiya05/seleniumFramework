package seleniumframework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
 
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
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
	
	public void loginApplication(String username, String password)
	{
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		submitButton.click();
	
	}
	public void goTo(String url)
	{
		driver.get(url);
	}
	
}
