package seleniumframework.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframework.AbstractComponent.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".form-group")
	WebElement searchCountry;

	@FindBy(css = "section[class*='list-group'] button")
	List<WebElement> countryList;

	@FindBy(css = ".action__submit")
	WebElement submit;
	By findBy = By.cssSelector("section[class*='list-group']");
	
	@FindBy(xpath="(//section[@class='ta-results list-group ng-star-inserted']//button)[2]")
	WebElement countrySelect;
	
	public void selectCountry(String countryName) {
		
		
		Actions a = new Actions(driver);
		a.sendKeys(searchCountry, countryName).build().perform();
		waitForElementToBeAppear(findBy);
		countrySelect.click();
//		for (int i = 0; i < countryList.size(); i++) {
//
//			String option = countryList.get(i).findElement(By.cssSelector("span")).getText();
//			if (option.equals(countryName)) {
//				countryList.get(i).click();
//			} 
//		}
	}

	public ConfirmationPage submitOrder() {
		Actions a1 = new Actions(driver);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		
//		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);

		a1.moveToElement(submit).click().build().perform();
		return new ConfirmationPage(driver);
	}

}
