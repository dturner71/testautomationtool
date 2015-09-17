package Scripts.iPad;

import java.lang.reflect.Method;
import lib.iPadLibrary;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class LoginTests extends iPadLibrary{
	@BeforeSuite
	public void beforeSuite(ITestContext context){

	}
	@BeforeTest
	public void beforeTest(ITestContext context){
		createDriver(context,true,true);
		setupEnveroment(context);
	
	}
	private String 	 testName;
	@BeforeMethod
	public void beforeMethod(Method method){
		testName = method.getName(); 
		setMoble(true);
		setWaitTime(1);
		//testName +":
	}
	@AfterTest
	public void aftertest (){
		
	}
	@Test(priority=1)
	public void test01() {
		
	}

}
