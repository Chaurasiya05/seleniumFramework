package seleniumframework;

import java.awt.Desktop.Action;
import java.awt.Window;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumframework.PageObject.CartPage;
import seleniumframework.PageObject.CheckoutPage;
import seleniumframework.PageObject.ConfirmationPage;
import seleniumframework.PageObject.LandingPage;
import seleniumframework.PageObject.ProductCatalogue;
import seleniumframework.TestComponents.BaseClass;

public class ErrorValidationTest extends BaseClass {
	
	String productName = "ADIDAS ORIGINAL";
	@Test
	public void ProductErrorValidation () throws InterruptedException
	{
		ProductCatalogue productCatalogue=	landingpage.loginApplication("anubhavchaurasiya22@gmail.com", "Anubhav@123");
		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);
		CartPage cartpage=	productCatalogue.goToCart();
		Boolean match = cartpage.verifyProductDisplay("ADIDAS ORIGINAL123");
		Assert.assertFalse(match);
	}
	@Test
	public void loginErrorValiation() 
    {
	landingpage.loginApplication("anubhavc@gmail.com","Anubhav123");
	Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage());
	System.out.println(landingpage.getErrorMessage());
    }
	
	
}


