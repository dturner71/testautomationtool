package Scripts.android;

import java.lang.reflect.Method;
import lib.iPadLibrary;
import objectSelector.Directions.direction;
import screen.loginScreen;

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
	public void test1_2_1() {
		testTitle("1.2.1: Verfiy Onboading screen loads");
		setWaitTime(60);
		evaluateTest(doesWebElementExist(loginScreen.pageOne), "Found Onboarding screen");
	}

	@Test(priority=2)
	public void test1_3_1() {
		testTitle("1.3.1:Verify Next button functionality");
		singleTapWebElement(loginScreen.nextButton);
		evaluateTest(doesWebElementExist(loginScreen.pageTwo), "Found Page two");
	}
	@Test(priority=3)
	public void test1_4_1() {
		testTitle("1.4.1:Verify Next button functionality");
		singleTapWebElement(loginScreen.nextButton);
		evaluateTest(doesWebElementExist(loginScreen.pageThree), "Found Page Three");
	}
	@Test(priority=4)
	public void test1_5_1() {
		testTitle("1.5.2:Verify  Swip right functionality");
		swipeInDirecton(direction.right); //swipe twice to get back to page one
		evaluateTest(doesWebElementExist(loginScreen.pageTwo), "Found Onboarding screen");
		swipeInDirecton(direction.right);
		evaluateTest(doesWebElementExist(loginScreen.pageOne), "Found Onboarding screen");
	}

	@Test(priority=5)
	public void test1_3_2() {		
		swipeInDirecton(direction.left);
		evaluateTest(doesWebElementExist(loginScreen.pageTwo), "Found Page two");
	}

	@Test(priority=6)
	public void test1_4_2() {
		testTitle("1.4.2:Verify  Swip Left functionality");
		swipeInDirecton(direction.left);
		evaluateTest(doesWebElementExist(loginScreen.pageThree), "Found Page Three");
	}

	@Test(priority=7)
	public void test1_5_2() {
		testTitle("1.5.2:Verify Get Started button functionality");
		singleTapWebElement(loginScreen.getStarted);
		evaluateTest(doesWebElementExist(loginScreen.countrySelectScreen), "Found country select screen");
	}
}
