package Scripts.android;

import java.lang.reflect.Method;
import java.util.List;

import lib.AndroidLibrary;
import objectSelector.Directions.direction;
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



public class CountrySupportedTest extends AndroidLibrary{

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
		setWaitTime(10);
		//testName +":
	}
	@AfterTest
	public void aftertest (){
		quit();
	}
	@Test(priority=1)
	public void test1_8_5() {
		testTitle("1.8.5: Verify flow for Suported location");
		singleTapWebElement(countryScreen.countryButton);
		enterText("Ph", countryScreen.searchBox);
		singleTapWebElement(countryScreen.Supported);
		singleTapWebElement(countryScreen.nextButton);
		evaluateTest(doesWebElementExist(countryScreen.signUpTitle), "Verify flow for unsuported location");
	}

	@Test(priority=2)
	public void test1_9a_2() {
		testTitle("1.9-a.2: Log in with name/email");
		hideKeyboard();
		enterText("otto.mator@mailinator.com", loginScreen.emailNameText); // start at the bottom so all 3 fields are on screen when the keyboard is up
		enterText("Otto", loginScreen.firstNameText);
		enterText("Mator", loginScreen.lastNameText);
		singleTapWebElement(loginScreen.nextButton);
		evaluateTest(doesWebElementExist(loginScreen.pinPageOneText), "Verify enter Pin Page");
	}
	@Test(priority=3)
	public void test1_10_2() {
		testTitle("1.10.2:Verify error with  too few numbers");
		enterPinPad("2");
		enterPinPad("5");
		enterPinPad("8");
		enterPinPad("Enter");
		evaluateTest(doesWebElementExist(loginScreen.pinPageOneText), "Should stay on the same screen");
	}
	@Test(priority=3)
	public void test1_10_3() {
		testTitle("1.10.3 Delete number from pin");
		enterPinPad("Delete");
		evaluateTest(!doesWebElementExist(loginScreen.pin3), "Verify enter number is deleted");
	}
	@Test(priority=4)
	public void test1_11_1() {
		testTitle("1.11.1: enter mismatched Pin");
		enterPinPad("8");
		enterPinPad("0");
		enterPinPad("Enter");
		enterPinPad("8");
		enterPinPad("0");
		enterPinPad("8");
		enterPinPad("0");
		enterPinPad("Enter");
		evaluateTest(doesWebElementExist(loginScreen.pinPageTwoText), "Verify Should stay on the same screen");
	}
	@Test(priority=5)
	public void test1_11_2() {
		testTitle("1.11.2: Back Button");
		singleTapWebElement(loginScreen.backButton);
		singleTapWebElement(loginScreen.backButton);
		evaluateTest(doesWebElementExist(loginScreen.firstNameText), "Verify Should return to sign up screen");


	}
}
