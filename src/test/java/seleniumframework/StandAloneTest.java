package seleniumframework;

import java.awt.Desktop.Action;
import java.awt.Window;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	public static void main(String[] args) {

		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("anubhavchaurasiya22@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anubhav@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".offset-sm-1.mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap h3"));

		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("[style*='list-style'] button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='list-group']")));
		List<WebElement> countryList = driver.findElements(By.cssSelector("section[class*='list-group'] button"));
//       WebElement CountryMatched=(WebElement) countryList.stream().filter(country->country.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("India"));
//      CountryMatched.click();

		for (int i = 0; i < countryList.size(); i++) {

			String option = countryList.get(i).findElement(By.cssSelector("span")).getText();
			if (option.equals("India")) {
				countryList.get(i).click();
			}

		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.cssSelector(".action__submit")));
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("h1[style*='text-transform']")).getText(),
				"THANKYOU FOR THE ORDER.");
		driver.close();
	}
}
