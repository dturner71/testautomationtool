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
import screen.mainScreen;

import org.jf.baksmali.main;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.remote.MobileBrowserType;



public class CountryPartiallySupported extends AndroidLibrary{

	@BeforeTest
	public void beforeTest(ITestContext context){
		createDriver(context,true,true);
		setupEnveroment(context);
		setWaitTime(30);
		loginNumberProxy();
		emptyInbox();;
		singleTapWebElement(loginScreen.nextButton);
		singleTapWebElement(loginScreen.nextButton);
		singleTapWebElement(loginScreen.getStarted);
	}
	private String 	 testName;
	@BeforeMethod
	public void beforeMethod(Method method){
		testName = method.getName(); 
		setMoble(true);
		setWaitTime(5);
		//testName +":
	}
	@AfterTest
	public void aftertest (){
		quit();
	}
	@Test(priority=1)
	public void test1_8_6() {
		testTitle("1.8.5:Verify flow for Partially Supported location");
		if(!doesWebElementExist(countryScreen.usa)){
			singleTapWebElement(countryScreen.countryButton);
			enterText("usa", countryScreen.searchBox);
			singleTapWebElement(countryScreen.Partially);
		}
		singleTapWebElement(countryScreen.nextButton);
		evaluateTest(doesWebElementExist(countryScreen.setUpAbraButton), "Verify flow for Partially location (set up button)");
		evaluateTest(doesWebElementExist(countryScreen.getNotifiedButton), "Verify flow for Partially location (Get notifed button)");
	}

	@Test(priority=2)
	public void test1_9a_2() {
		testTitle("1.9-a.2: Log in with name/email");
		singleTapWebElement(countryScreen.setUpAbraButton);
		hideKeyboard();
		enterText("otto.mator@mailinator.com", loginScreen.emailNameText); // start at the bottom so all 3 fields are on screen when the keyboard is up
		enterText("Otto", loginScreen.firstNameText);
		enterText("Mator", loginScreen.lastNameText);
		singleTapWebElement(loginScreen.nextButton);
		evaluateTest(doesWebElementExist(loginScreen.pinPageOneText), "Verify enter Pin Page");
	}
	@Test(priority=3)
	public void test1_10_1() {
		testTitle("1.10.1:Verify Enter pin functionality");
		enterPinPad("2");
		enterPinPad("5");
		enterPinPad("8");
		enterPinPad("0");
		enterPinPad("Enter");
		evaluateTest(doesWebElementExist(loginScreen.pinPageTwoText), "Verify enter Pin Page");
	}
	@Test(priority=3)
	public void test1_11_1() {
		testTitle("1.11.1:Verify confirm pin");
		enterPinPad("2");
		enterPinPad("5");
		enterPinPad("8");
		enterPinPad("0");
		enterPinPad("Enter");
		evaluateTest(doesWebElementExist(loginScreen.phoneScreen), "Verify enter Pin Page");
	}
	@Test(priority=4)
	public void test1_12_1() {
		testTitle("1.12.1:Verify enter Phone Number");
		enterText("4083897191", loginScreen.phoneNumberTxt);
		singleTapWebElement(loginScreen.nextButton);
		evaluateTest(doesWebElementExist(loginScreen.enterCode), "Verify enter code screen");
	}
	@Test(priority=5)
	public void test1_13_1() {
		testTitle("1.13.1:Verify recive and enter code");
		setMoble(false);
		refreshWebpage();
		String code = getCode();
		setMoble(true);
		enterText(code, loginScreen.verifyCode);
		singleTapWebElement(loginScreen.nextButton);
		evaluateTest(doesWebElementExist(mainScreen.profilePic), "Verify Page 1.16 loads");

	}

}
