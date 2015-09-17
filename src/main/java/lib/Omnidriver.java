package lib;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import objectSelector.ObjectSelector;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Omnidriver {
	private AppiumDriver adriver;
	private static FirefoxDriver browserDirver;
	private	static boolean isiOSdevice = false;
	private static boolean isMobile = false;
	private static boolean isbrowser = false;
	private static boolean isiPhone= false;
	private static int waitTime = 1;

	public boolean isiOSdevice(){
		return isiOSdevice;
	}

	public Omnidriver(ITestContext context, boolean mobile , boolean browser){
		isMobile=mobile;
		isbrowser=browser;
		if(isbrowser){
			browserDirver = new FirefoxDriver();
		}
		if(mobile){
			adriver = createAppiumDriver(context);
		}
	}
	public boolean  isMobile(){
		return  isMobile ;
	}
	public boolean isiPhone(){
		return isiPhone;
	}
	public void navigate(String url){
		browserDirver.navigate().to(url);
	}

	public void setMoble(boolean mode){
		if(mode){
			if (adriver==null){ // if there is no appium drive don't go moble
				isMobile =false;
				return;
			}
		}else{
			if(browserDirver==null){//if there is no browser drive don't go web
				isMobile =true;
				return;
			}
		}
		isMobile =mode;
	}
	public AppiumDriver getAppuimDriver(){
		return adriver;
	}
	public FirefoxDriver getBrowserDirver(){
		return browserDirver;
	}
	//	try {
	//		if(isMoble){
	//			return adriver.;
	//		} else {
	//			return ffdirver.;
	//		}
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//		return null;
	//	}

	public void swipe(Point start, Point end, int speed){
		if(isMobile){
			adriver.swipe(start.x, start.y, end.x, end.y, speed);
		} else {
			System.out.println("Not supported");
		}
	}

	public Keyboard getKeyboard(){
		try {
			if(isMobile){
				return adriver.getKeyboard();
			} else {
				return browserDirver.getKeyboard();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Dimension getScreenSize(){
		if(isMobile){
			return adriver.manage().window().getSize();
		} else {
			return browserDirver.manage().window().getSize();
		}
	}

	public List<WebElement> findElementsByClassName(String iOSElementClass){
		List<WebElement> list = new ArrayList<WebElement>();
		try {
			if(isMobile){
				list = adriver.findElementsByClassName(iOSElementClass);
			} else {
				list = browserDirver.findElementsByTagName(iOSElementClass);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<WebElement> collectWebElementsBySelector(ObjectSelector selectorName){
		String type = selectorName.getSelectorType();
		try {
			if(isMobile){
				switch (type) {
				case "AccessibilityId":
					return adriver.findElementsByAccessibilityId(selectorName.getUinqueID());	
				case "ID":
					return adriver.findElementsById(selectorName.getUinqueID());
				case "className":
					return adriver.findElementsByClassName(selectorName.getUinqueID());
				case "name":
					return adriver.findElementsByName(selectorName.getUinqueID()); 
				case "xpath":
					return adriver.findElementsByXPath(selectorName.getUinqueID()); 
				default:
					System.out.println("Not Supported " +type);
				}
			} else {
				switch (type) {
				case "AccessibilityId":
					List<WebElement> elements = browserDirver.findElementsByTagName(selectorName.getWebClass());
					String	text = selectorName.getWebText();
					List<WebElement> accID = new ArrayList<WebElement>();
					for(WebElement ele : elements){
						if(ele.getText().contains(text)){
							accID.add(ele);
						}
					}
					if (accID.size()==0) {
						return null;
					}
					return accID;	
				case "ID":
					return browserDirver.findElementsById(selectorName.getUinqueID());
				case "className":
					return browserDirver.findElementsByClassName(selectorName.getUinqueID());
				case "name":
					return browserDirver.findElementsByName(selectorName.getUinqueID()); 
				case "xpath":
					return browserDirver.findElementsByXPath(selectorName.getUinqueID()); 
				case "CssSelector":
					return browserDirver.findElementsByCssSelector(selectorName.getUinqueID()); 
				default:
					System.out.println("Not Supported " +type);
				}
			}
		} catch (Exception e) {
			//			dumpAll(collentWebElements(iOSElementClass.UIATextField));
			//		log("Element not found");
		}
		return null;
	}// end of method

	public WebElement findWebelementBySelector(ObjectSelector selector){
		String type = selector.getSelectorType();
		try {
			if(isMobile){
				switch (type) {
				case "AccessibilityId":
					return adriver.findElementByAccessibilityId(selector.getUinqueID());
				case "ID":
					return adriver.findElementById(selector.getUinqueID());
				case "className":
					return adriver.findElementByClassName(selector.getUinqueID());
				case "name":
					return adriver.findElementByName(selector.getUinqueID()); //		log("name: " + obj.getAttribute("name"));
				case "xpath":
					return adriver.findElementByXPath(selector.getUinqueID()); 
				default:
					System.out.println("not supported " + type);
					break;
				}
			}else{
				switch (type) {
				case "AccessibilityId":
					List<WebElement> elements = browserDirver.findElementsByTagName(selector.getWebClass());
					String	text = selector.getWebText();
					for(WebElement ele : elements){
						if(ele.getText().contains(text)){
							return ele;
						}
					}
					return null;
				case "ID":
					return browserDirver.findElementById(selector.getUinqueID());
				case "className":
					return browserDirver.findElementByClassName(selector.getUinqueID());
				case "name":
					return browserDirver.findElementByName(selector.getUinqueID()); //		log("name: " + obj.getAttribute("name"));
				case "xpath":
					return browserDirver.findElementByXPath(selector.getUinqueID()); 
				case "CssSelector":
					return browserDirver.findElementByCssSelector(selector.getUinqueID()); 

				default:
					System.out.println("not supported " + type);
					break;
				}
			}
		} catch (Exception e) {
			//			dumpAll(collentWebElements(iOSElementClass.UIATextField));
			//		log("Element not found");
		}
		return null;
	}// end of method

	public boolean tap(int fingers, WebElement obj, int duration){
		try {
			if(isMobile){
				adriver.tap(fingers,obj,duration);
				return true;
			} else {
				obj.click();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean tap (int fingers, Point tapHere, int duration){//tap(int fingers, int x, int y, int duration)
		if(isMobile){
			adriver.tap(fingers,tapHere.x,tapHere.y,duration);
			return true;
		} else {
			return false;
		}

	}
	public File grabScreen (){

		if(isMobile){
			if (adriver==null){ // if there is no appium driver were runing web only
				return browserDirver.getScreenshotAs(OutputType.FILE);
			}
			return adriver.getScreenshotAs(OutputType.FILE);
		} else {
			if (browserDirver==null){ //if there is no browser driver we running moble only
				return adriver.getScreenshotAs(OutputType.FILE);
			}
			return browserDirver.getScreenshotAs(OutputType.FILE);
		}
	}

	protected void setWaitTime (int seconds){
		waitTime = seconds;
		if(isMobile){
			adriver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		} else {
			browserDirver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		}
	}

	protected int getWaitTime(){
		return waitTime;
	}


	private AppiumDriver createAppiumDriver(ITestContext context){
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		String url= "http://127.0.0.1:4723/wd/hub";

		String platformName = context.getCurrentXmlTest().getParameter("platformName");
		String platformVersion = context.getCurrentXmlTest().getParameter("platformVersion");
		String browserName = context.getCurrentXmlTest().getParameter("browserName");
		String bundleId = context.getCurrentXmlTest().getParameter("bundleId");
		String appPackage = context.getCurrentXmlTest().getParameter("appPackage");
		String appActivity = context.getCurrentXmlTest().getParameter("appActivity");
		String fullReset = context.getCurrentXmlTest().getParameter("fullReset");
		String udid =System.getProperty("udid");  //check the command line 
		String	app =System.getProperty("app");   //check the command line 
		String deviceName =System.getProperty("deviceName");   //check the command line 

		if (app == null){ //check to see if this was passed from the command line
			app = context.getCurrentXmlTest().getParameter("app");
		}
		if (deviceName == null){ //check to see if this was passed from the command line
			deviceName = context.getCurrentXmlTest().getParameter("deviceName");
		}
		if (udid == null){ //check to see if this was passed from the command line
			udid = context.getCurrentXmlTest().getParameter("udid");	
		}

		try {
			if(deviceName.toString().contains("iPhone")){
				isiPhone=true;
			}
		} catch (Exception e) {
			System.out.println("No Device specified. Asuming iPad");
		}
		capabilities.setCapability("interKeyDelay", 5);
		capabilities.setCapability("deviceName", deviceName );
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName",platformName);
		capabilities.setCapability("browserName",browserName);
		capabilities.setCapability("udid", udid);
		capabilities.setCapability("app", app);
		capabilities.setCapability("fullReset", fullReset);
		try {
			if (platformName.toString().contains("iOS")){
				capabilities.setCapability("bundleId", bundleId);

				isiOSdevice=true;
				adriver = new IOSDriver(new URL(url), capabilities);	
				adriver.rotate(ScreenOrientation.PORTRAIT); //app should run in Portrait mode on iPhone	
				return adriver;
			}else{
				capabilities.setCapability("appPackage", appPackage);
				capabilities.setCapability("appActivity", appActivity);
				adriver = new AndroidDriver(new URL(url), capabilities);
				adriver.rotate(ScreenOrientation.PORTRAIT); //app should run in landscape mode. 
				isiOSdevice=false;
				return adriver;
			}

		} catch (Exception e) {
			//		LOG.error("Failed to instantiate appium driver", e);
			e.printStackTrace();;
			quit();
		}
		return null;
	}

	public void relauchApp(){
		adriver.closeApp();
		adriver.launchApp();
	}

	public void closeApp(){
		try {
			if(adriver != null){ //null check so we don't try to close a null driver
				adriver.closeApp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void launchApp() {
		try {
			if(isMobile){
				adriver.launchApp();	
			}else{
				System.out.println("Not Supproted");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void quit(){

		if(adriver!=null){
			try {

				adriver.quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (browserDirver != null){
			try {
				browserDirver.quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public String getPageSource(){
		try {
			if(isMobile){
				System.out.println("HTML only");
				return "HTML only";
			} else {
				return	browserDirver.getPageSource();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	//	public void doubleTap(Double x, Double y, Double duration){
	public void doubleTap(WebElement element){
		JavascriptExecutor jsExecutor = (JavascriptExecutor) adriver;
		HashMap<String,Double> action = new HashMap<String, Double>();
		//(JavascriptExecutor)wd.executeScript("mobile: tap", new HashMap<String, Double>() {{ put("tapCount", 2); put("touchCount", 1); put("duration", 0.5); put("x", 176); put("y", 491); }});
		//		((RemoteWebDriver)driver).executeScript("au.getElement('" + element.getId() + "').tapWithOptions({tapCount:2});");
		//		action.put("tapCount", 2.0);
		//		action.put("touchCount", 1.0);
		//		action.put("duration", duration);
		//		action.put("x", x);
		//		action. put("y", y);
		//		jsExecutor.executeScript("mobile: tap", action);
		//		adriver.executeScript("au.getElementsByAccessibilityId('Touch to Start').tapWithOptions({tapCount:2});");		
	}

	public void changeField(WebElement element){
		JavascriptExecutor jsExecutor = (JavascriptExecutor) browserDirver;
		try {
			jsExecutor.executeScript("$(arguments[0]).change();", element);
		} catch (Exception e) {
			//	e.printStackTrace();
		}
	}
	public void goBack(){
		if(isMobile){
			adriver.navigate().back();
		}else{
			browserDirver.navigate().back();
		}
	}
	public Boolean refreshWebPage(){
		try {
			browserDirver.navigate().refresh();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public void hideKeyboard(){
		adriver.hideKeyboard();
	}
}
