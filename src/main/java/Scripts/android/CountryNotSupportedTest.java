package Scripts.android;

import java.lang.reflect.Method;
import java.util.List;

import lib.AndroidLibrary;
import objectSelector.ObjectTypes.SelectorType;
import objectSelector.ObjectTypes.androidWidget;
import objectSelector.ObjectTypes.iOSElementClass;
import screen.countryScreen;
import screen.loginScreen;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class CountryNotSupportedTest extends AndroidLibrary{

	@BeforeTest
	public void beforeTest(ITestContext context){
		createDriver(context,true,false);
		setupEnveroment(context);
		setWaitTime(30);
		singleTapWebElement(loginScreen.nextButton);
		singleTapWebElement(loginScreen.nextButton);
		singleTapWebElement(loginScreen.getStarted);
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
	public void test1_8_4() {
		testTitle("1.8.4: Verify flow for unsuported location");
		singleTapWebElement(countryScreen.countryButton);
		singleTapWebElement(countryScreen.unsuported);
		singleTapWebElement(countryScreen.nextButton);
		evaluateTest(doesWebElementExist(countryScreen.getNotified), "Verify flow for unsuported location");
	}

	@Test(priority=2)
	public void test1_20_1() {
		testTitle("1.20.1:Sign up to recive email");
		int number = returnRandom(999);
		enterText("tester"+ number +"@mailinator.com", countryScreen.emailTextField);
		hideKeyboard();
		singleTapWebElement(countryScreen.nextButton);
	}
	@Test(priority=3)
	public void test1_22_1() {
		testTitle("1.22.1:Verify Next button functionality");
		String[] referenceText = {"Thank You!","Thanks for your interest in Abra. We will notify you as soon as Abra is available in your area.","Share the love"};
		List<String> screenText = collectTextFrom(collectWebElements(androidWidget.TextView), SelectorType.AccessibilityId, true);
		evaluateTest(verifyScreenText(screenText, referenceText), "Verifying all text");
	}
}
