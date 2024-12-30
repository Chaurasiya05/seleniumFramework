package seleniumframework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumframework.DataProvider.DataReader;
import seleniumframework.PageObject.LandingPage;

public class BaseClass {

	public WebDriver driver;
	public LandingPage landingpage;
	public Properties prop;
	public String url;
	public WebDriver initDriver() throws IOException {
		prop = new Properties();
//		FileInputStream path = new FileInputStream(System.getProperty("user.dir")
//				+ "/src\\main\\java\\seleniumframework\\Resource\\GlobalData.properties");
//		prop.load(path);
	    prop=getPropertyObject(prop);
	    String browserName = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		 browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initDriver();
		url=prop.getProperty("url");
		landingpage = new LandingPage(driver);
		landingpage.goTo(url);
		
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
	driver.quit();
	}
	public List<HashMap<String, String>> getJsonDataMap(String filepath) throws IOException
	{
		
		//reading json to String
		
		String jsonContent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_16);
	
	    // reading String to map Jackson  databind
		ObjectMapper mapper= new ObjectMapper();
	   List<HashMap<String, String>> data=	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
	return data;
	}
	
	public String getScreenshot(String testcase,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 File file = new File(System.getProperty("user.dir")+"//reports//"+testcase+".png");
		 FileUtils.copyFile(source, file);
		 return System.getProperty("user.dir")+"//reports//"+testcase+".png";
	}
	
	public static Properties getPropertyObject(Properties prop) throws IOException
	{
		FileInputStream path = new FileInputStream(System.getProperty("user.dir")
				+ "/src\\main\\java\\seleniumframework\\Resource\\GlobalData.properties");
		prop.load(path);
		return prop;
	}
}
