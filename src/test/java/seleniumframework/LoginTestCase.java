package seleniumframework;

import org.testng.annotations.Test;

import seleniumframework.TestComponents.BaseClass;

public class LoginTestCase  extends BaseClass{
	@Test
 public void Login()
 {
		landingpage.loginApplication("anubhavchauraiya2222@gmail.com","AnubhavC@123");
	    
 }
}
