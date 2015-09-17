package Scripts.mics;

import java.lang.reflect.Method;
import lib.iPadLibrary;
import lib.mics;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class teller extends mics{
	@BeforeSuite
	public void beforeSuite(ITestContext context){

	}
	@BeforeTest
	public void beforeTest(ITestContext context){
		createDriver(context,true,false);
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
		log("Starting tests");
		enterPinPad("2");
		enterPinPad("5");
		enterPinPad("8");
		enterPinPad("0");
		enterPinPad("Enter");
		
		
	}

}
