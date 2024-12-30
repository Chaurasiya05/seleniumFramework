package seleniumframework.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumframework.AbstractComponent.AbstractComponents;

public class ProductCatalogue  extends AbstractComponents{

	By productsBy=By.cssSelector(".mb-3");
	By toastMessage=By.cssSelector("#toast-container");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".offset-sm-1.mb-3")
	List<WebElement>  products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	public List<WebElement> getProductList()
	{
		waitForElementToBeAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName)
	{
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToBeAppear(toastMessage);
		waitForElementToBeDisappear();
		
	}


}
