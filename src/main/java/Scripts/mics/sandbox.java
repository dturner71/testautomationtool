package Scripts.mics;

import java.lang.reflect.Method;
import java.util.List;

import lib.iPadLibrary;
import lib.mics;
import objectSelector.ObjectTypes.SelectorType;
import screen.loginScreen;
import screen.numberProxy;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class sandbox extends mics{
	@BeforeSuite
	public void beforeSuite(ITestContext context){

	}
	@BeforeTest
	public void beforeTest(ITestContext context){
		createDriver(context,false,true);
		setupEnveroment(context);

	}
	private String 	 testName;
	@BeforeMethod
	public void beforeMethod(Method method){
		testName = method.getName(); 
		setMoble(false);
		setWaitTime(1);
		//testName +":
	}
	@AfterTest
	public void aftertest (){
		closeAll();
	}
	@Test(priority=1)
	public void test01() {
		log("Starting tests");
		loginNumberProxy();
		setMoble(false);
		String numbers = getCode();
		log(numbers);
		
	}

}
