package seleniumframework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumframework.TestComponents.BaseClass;

public class LoginErrorValidationTest extends BaseClass {

	@Test(dataProvider = "getData")
    public void loginErrorValiation(String email, String password) 
    {
	landingpage.loginApplication(email,password);
	Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	System.out.println(landingpage.getErrorMessage());
    }
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

//	HashMap<Object, Object> map = new HashMap<Object, Object>();
//	    map.put("email", "anubhavchaurasiya2222@gmail.com");
// 		map.put("password", "Anubhav@123");
//		
//		
////        List<HashMap<String, String>> data=getJsonDataMap(System.getProperty("user.dir")+"//src//test//java//seleniumframework//DataProvider//Login.json");
//    	return new Object[][] {{map.get(0)}};
		return new Object[][] {{"anubhavchaurasiya2222@gmail.com","Anubhav@123"}};
	}
	
	
}
