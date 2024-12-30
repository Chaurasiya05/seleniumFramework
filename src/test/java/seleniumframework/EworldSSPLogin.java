package seleniumframework;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EworldSSPLogin {
  private static String LoungeName="Revanth";
  private static String  LoginPageTitle="Login";
  private static String  LoungePageTitle="Oneworld Shared Servcices Lounge Administration";
  private static String  LogoutPageTitle="Not found";
	public static void main(String[] args) {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://ssp-staging.oneworld.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    Assert.assertEquals(LoginPageTitle, driver.getTitle());
	    System.out.println("Validation 1 passed:");
		
		driver.findElement(By.cssSelector("#email")).sendKeys("manisham@zen3.co.uk");
		driver.findElement(By.cssSelector("#Password")).sendKeys("Test@1234567");
		driver.findElement(By.cssSelector("button[name='submit']")).click();
		Assert.assertEquals(LoungePageTitle, driver.getTitle());
		System.out.println("Validation 1 passed:");
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tbody tr td:first-child")));
        List<WebElement> loungeLinks=driver.findElements(By.cssSelector("tbody tr td:first-child a"));
	 
       // List<WebElement> lounge=  loungeList.stream().filter(list->list.getText().equalsIgnoreCase(LoungeName)).collect(Collectors.toList());
        String loungeName=  loungeLinks.get(0).getText();
        Assert.assertTrue(loungeLinks.get(0).isEnabled());
        loungeLinks.get(0).click();
        WebElement InLounge = null;
        try {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#Lounge_Name")));
        InLounge = driver.findElement(By.cssSelector("#Lounge_Name"));
        }
        catch (Exception e) {
        	
        	Assert.fail("Lounge is not opening");
        }
        System.out.println("Validation 3rd is passed");
        String nameInLounge= InLounge.getAttribute("value");
        System.out.println(nameInLounge);
        Assert.assertEquals(loungeName, nameInLounge);
        System.out.println("Validation 4 is passed");
        driver.findElement(By.cssSelector("#logout")).click();
        Assert.assertEquals(LogoutPageTitle, driver.getTitle());
        driver.close();
        
	}
}
