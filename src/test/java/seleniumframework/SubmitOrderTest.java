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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumframework.PageObject.CartPage;
import seleniumframework.PageObject.CheckoutPage;
import seleniumframework.PageObject.ConfirmationPage;
import seleniumframework.PageObject.LandingPage;
import seleniumframework.PageObject.OrderPage;
import seleniumframework.PageObject.ProductCatalogue;
import seleniumframework.TestComponents.BaseClass;

public class SubmitOrderTest extends BaseClass {
	String productName = "ADIDAS ORIGINAL";
	String CountryName="India";
	@Test(dataProvider = "getData",groups = "Purchase")
         public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
         {
		
		ProductCatalogue productCatalogue=	landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartpage=	productCatalogue.goToCart();
		Boolean match = cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage= cartpage.goToCheckout();
		checkoutpage.selectCountry(CountryName);
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();
		Assert.assertEquals(confirmationpage.getConfirmationMessage(),
				"THANKYOU FOR THE ORDER.");
	
		}
	
	@Test(priority = 2)
	public void OrderHistoryValidation()
	{
		ProductCatalogue productCatalogue=	landingpage.loginApplication("anubhavchaurasiya22@gmail.com", "Anubhav@123");
		OrderPage orderpage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	
    }
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<Object, Object> map = new HashMap<Object, Object>();
//		map.put("email", "anubhavchaurasiya22@gmail.com");
//		map.put("password", "Anubhav@123");
//		map.put("product", "IPHONE 13 PRO");
//		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
//		map1.put("email", "anubhavchaurasiya222@gmail.com");
//		map1.put("password", "Anubhav@1223");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		return  new Object[][] {{map},{map1}};
        List<HashMap<String, String>> data=getJsonDataMap(System.getProperty("user.dir")+"/src\\test\\java\\seleniumframework\\DataProvider\\PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};	
	}
}





//return new Object[][] {{"anubhavchaurasiya22@gmail.com", "Anubhav@123","ADIDAS ORIGINAL"},{"anubhavchaurasiya222@gmail.com","Anubhav@1223","ADIDAS ORIGINAL"}};	


//HashMap<Object, Object> map = new HashMap<Object, Object>();
//map.put("email", "anubhavchaurasiya22@gmail.com");
//map.put("password", "Anubhav@123");
//map.put("product", "IPHONE 13 PRO");
//HashMap<Object, Object> map1 = new HashMap<Object, Object>();
//map1.put("email", "anubhavchaurasiya222@gmail.com");
//map1.put("password", "Anubhav@1223");
//map1.put("productName", "ADIDAS ORIGINAL");