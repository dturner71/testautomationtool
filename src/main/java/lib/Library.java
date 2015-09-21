package lib;


import objectSelector.Directions.direction;
import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;
import objectSelector.ObjectTypes.iOSElementClass;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.SkipException;
import lib.Omnidriver;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.testng.AssertJUnit.assertTrue;


public class Library  {
	protected static Logger LOG = LoggerFactory.getLogger(Library.class);
	protected static Omnidriver omnidriver=null;
	protected static Dimension screenSize;
	//	protected static boolean isiOSdevice;
	//	protected static boolean isiPhone;
	protected static String testOutputDirectory;
	protected static String dateDirectory;
	protected static String timeDirecory;

	public boolean createDriver(ITestContext context,boolean mobile , boolean browser){
		omnidriver = new Omnidriver(context,mobile,browser);
		if (omnidriver == null){
			log("Driver failed to init");
			return false;
		}
		return true;
	}
	public boolean isiPhone(){
		return omnidriver.isiPhone();
	}
	public void navagateToWebpage(String url) {
		if(omnidriver.isMobile()){
			log("Not in mobile");
		}
		log("Navigateing to " + url);
		omnidriver.navigate(url);
	}

	protected void setWaitTime (int seconds){
		if (getWaitTime() == seconds){
			return; // if new time == old time
		}
		log("Setting wait time to " + seconds);
		omnidriver.setWaitTime(seconds);
	}
	protected int getWaitTime(){
		return omnidriver.getWaitTime();
	}

	public void setMoble(boolean mode){
		omnidriver.setMoble(mode);
		log("Moble set to: " + mode,true);
	}
	protected void setupEnveroment(ITestContext context){
		screenSize = omnidriver.getScreenSize();
		testOutputDirectory = context.getCurrentXmlTest().getParameter("testOutputDirectory");
		//time and date are used to group all of the screenshots from a run together. 
		Date date = new Date();
		DateFormat yearFormat = new SimpleDateFormat("yyyy_MM_dd"); 
		DateFormat timeFormat = new SimpleDateFormat("HHmm");
		dateDirectory =	yearFormat.format(date);
		timeDirecory = timeFormat.format(date);
		testTitle("Starting: "+context.getName());
	}

	public boolean	isiOSDevice(){
		return omnidriver.isiOSdevice();	
	}
	public void takeScreenShot(String title){
		File scrShot =	omnidriver.grabScreen();	
		int counter = 0;
		String screenShotPath =  "/tmp/screenShot/";
		String timeStampPath = dateDirectory + "/" + timeDirecory + "/";
		title.replaceAll(" ", "_");
		String saveFileName = title + "_"+ counter +".png";
		File saveFile = new File (screenShotPath + timeStampPath + saveFileName);

		while (saveFile.exists()){
			counter++;
			saveFileName = title + "_"+ counter +".png";
			saveFile = new File (screenShotPath + timeStampPath + saveFileName);
		}

		try {
			FileUtils.copyFile(scrShot, saveFile);
			screenSize = omnidriver.getScreenSize();
			int height = screenSize.height /3;
			int width  = screenSize.width /3 ;
			Reporter.log("<a href=\"" + screenShotPath +  timeStampPath + saveFileName + "\" target = \"_blank\">");
			Reporter.log("<img src=\"" + screenShotPath +  timeStampPath + saveFileName + "\" style=\"width:"+width+"px;height:"+height+"px\">");
			Reporter.log("</a>");

		} catch (IOException e) {
			log("Screen shot faild");
			e.printStackTrace();
		}
	}



	public void log(String text){
		log(text,false);
	}
	public void log(String text,boolean takeScreenShot){
		try {
			LOG.info(text);
			Reporter.log(text);
			//			System.out.println(text);
			if (takeScreenShot){
				takeScreenShot(text);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}
	public void testTitle (String text){
		Date date = new Date();
		DateFormat yearFormat = new SimpleDateFormat("MM/dd/yyyy"); 
		DateFormat timeFormat = new SimpleDateFormat("HH:mm a");
		String currentDate = yearFormat.format(date);
		String currentTime = timeFormat.format(date);
		log("----------");
		log(currentDate+" - "+currentTime);
		log(text);
		log("----------",true);
	}


	public void sleep(double time){
		try {
			long sleeptime = (long)(time*1000);
			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void dumpWebElement(WebElement obj,boolean includeHidden){
		if(!includeHidden && !obj.isDisplayed()){
			log("---------");
			log("hidden skipping");
			return;
		}
		try {
			log("---------");
			log("isDisplayed: " + obj.isDisplayed());
			log("isEnabled : " + obj.isEnabled());

			//			log("isSelected: " + obj.isSelected());
			log("Tag name: " + obj.getTagName());
			log("AccessibilityId: " + obj.getText());
			log("name: " + obj.getAttribute("name"));
			if (!omnidriver.isMobile()){
				log("ID: " + obj.getAttribute("ID"));
				log("href: "+obj.getAttribute("href"));
				log("class: "+obj.getAttribute("class"));				
			}
			if(omnidriver.isiOSdevice()){
				log("Value: " + obj.getAttribute("value"));
				log("Lable: " + obj.getAttribute("label"));
			}
			log("Loc: " + obj.getLocation().toString());
			log("Size: "+ obj.getSize().toString());
		} catch (Exception e) {
			e.printStackTrace();
			log("skip");
		}

	}
	public String getText(ObjectSelector selector, String SelectorType){
		return getText(findDisplayedWebelement(selector),SelectorType);
	}
	public String getText(WebElement WebElement, String SelectorType){
		String text = "Not Supportd";
		try {
			switch (SelectorType) {
			case "AccessibilityId":
				text = WebElement.getText();
				break;
			case "value":
				text = WebElement.getAttribute("value");
				break;
			case "name":
				text = WebElement.getAttribute("name");
				break;
			case "label":
				text = WebElement.getAttribute("label");
				break;
			default:
				text = "Not Supportd";
				break;
			}
		} catch (Exception e) {
			text = "null object";
		}
		return text;
	}

	public List<String> collectTextFrom(String iOSElementClass , String SelectorType, boolean includeHidden){
		return collectTextFrom(collectWebElements(iOSElementClass), SelectorType, includeHidden);
	}

	public String getTextFromSubCell (ObjectSelector parent, String iOSElementClass, String SelectorType){
		return getTextFromSubCell(findWebelementBySelector(parent), iOSElementClass, SelectorType);
	}

	public String getTextFromSubCell (WebElement parent, String iOSElementClass, String SelectorType){
		WebElement target = null;
		try {
			target = parent.findElement(By.className(iOSElementClass));
		} catch (Exception e) {
			e.printStackTrace();
			return "Null Object";	
		}
		String text = "Not Found";
		switch (SelectorType) {

		case "AccessibilityId":
			text = target.getText();
			break;

		case "value":
			text = target.getAttribute("value");
			break;

		case "name":
			text = target.getAttribute("name");
			break;

		case "label":
			text = target.getAttribute("label");
			break;

		default:
			text = "Not Supported";
			break;
		}
		return text;
	}

	public List<String> collectTextFrom(List<WebElement> elements , String SelectorType, boolean includeHidden){
		List<String> textFromElements = new ArrayList<String>();
		if(elements==null || elements.size()==0){
			log("no elements found");
			return textFromElements;
		}
		String text = new String();
		int counter = 0;
		int size= elements.size();
		log("elements Found: " + size,true);
		for (WebElement elm : elements){
			counter++;
			if (counter%10==0){
				log("Prossessing " + counter + " of " + size);
			}
			if (!elm.isDisplayed() && !includeHidden){ //its hidden and were excluding hidden
				continue;
			}
			switch (SelectorType) {
			case "AccessibilityId":
				text = elm.getText();
				if (text.length()>0){
					textFromElements.add(text);
				}
				break;
			case "name":
				text = elm.getAttribute("name");
				if (text.length()>0){
					textFromElements.add(text);
				}
				break;
			case "value":
				text = elm.getAttribute("value");
				if (text.length()>0){
					textFromElements.add(text);
				}
				break;
			default:
				log(SelectorType + " Not Supported");
				break;
			}
		}//end of for loop
		//		creatArrayString(textFromElements);
		log("Returning " + textFromElements.size());
		return textFromElements;
	}

	public void dumpAll (List<WebElement> list){
		dumpAll(list, true);
	}

	public void dumpAll (List<WebElement> list,boolean includeHidden){
		log("Found " + list.size() + " elements");
		for(WebElement elm :list){
			dumpWebElement(elm,includeHidden);
		}
	}

	private boolean singleTapRandomWebElement (WebElement obj, boolean includeHidden){
		try {
			Point loc = obj.getLocation();
			Dimension size = obj.getSize();
			Point tapHere =new Point(returnRandom(size.height) + loc.x , returnRandom(size.width)+loc.y);
			log("Center: " + returnCenterOfObj(obj));
			log("Tapping at " +tapHere.toString());
			if(!omnidriver.tap(1, tapHere, 250)){
				omnidriver.tap(1, obj, 250);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean singleTapWebElement (ObjectSelector selector){
		return singleTapWebElement(selector,false,false,true);
	}
	public boolean singleTapWebElement (ObjectSelector selector, boolean includeHidden){
		return singleTapWebElement(selector,false, includeHidden,true);
	}
	public boolean singleTapWebElement (ObjectSelector selector,boolean includeHidden, boolean screenShotOnFail){
		return singleTapWebElement(selector, false, includeHidden, screenShotOnFail);
	}
	public boolean singleTapWebElement (ObjectSelector selector,boolean randomLocation, boolean includeHidden, boolean screenShotOnFail){
		String discript = selector.getDiscriptions();
		if(includeHidden){
			if(!singleTapWebElement(findWebelementBySelector(selector),includeHidden)){
				log(discript + " not found",screenShotOnFail);
				if(includeHidden){
					sleep(.1);
				}
				return false;	
			}
		}else{
			if (!singleTapWebElement(findDisplayedWebelement(selector),randomLocation,includeHidden)){
				log(discript + " not found",screenShotOnFail);
				if(includeHidden){
					sleep(.1);
				}
				return false;
			}
		}
		log("Sending tap to " + discript);
		return true;

	}

	public boolean singleTapWebElement (WebElement parent, ObjectSelector selector)	{
		return singleTapWebElement(parent, selector,false,false);
	}

	public boolean singleTapWebElement (WebElement parent, ObjectSelector selector,boolean randomLocation, boolean includeHidden){
		String discript = selector.getDiscriptions();
		if (!singleTapWebElement(findWebelementBySelector(parent, selector),randomLocation,includeHidden)){
			log(discript + " not found",true);
			return false;
		}
		log("Sending tap to " + discript);
		return true;
	}

	public boolean singleTapWebElement (WebElement obj,  boolean includeHidden){
		return  singleTapWebElement(obj, false, includeHidden);
	}
	public boolean singleTapWebElement (WebElement obj, boolean randomLocation, boolean includeHidden){
		try{			
			if (!includeHidden && !obj.isDisplayed()){ 
				log("Object \""+ obj.getText() +"\" is hidden",true);
				return false;
			}
			if (randomLocation){
				return singleTapRandomWebElement(obj, includeHidden);
			}
			omnidriver.tap(1, obj, 250);
			sleep(1); // give time for gui to update
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Point returnCenterOfObj(WebElement obj){
		Point loc = obj.getLocation();
		Dimension size = obj.getSize();
		Point center =new Point(size.height/2 + loc.x , size.width/2 +loc.y);
		return center;
	}

	//	public boolean doubleTapWebElement (WebElement obj, boolean includeHidden){//TODO wait for fix from appium
	public boolean doubleTapWebElement(){
		//		omnidriver.doubleTap(100.0, 100.0, .5);
		WebElement start =	omnidriver.getAppuimDriver().findElementByAccessibilityId("Touch to Start");
		omnidriver.doubleTap(start);
		return true;
	}

	//	private TouchAction createTap(WebElement element, int duration) {
	//		TouchAction tap = new TouchAction(appuimDriver);
	//		return tap.press(element).waitAction(duration).release();
	//	}

	public WebElement findWebelementByPartialSelector(ObjectSelector objSelector, String iOSElementClass){
		return findWebelementByPartialSelector(collectWebElements(iOSElementClass), objSelector.getUinqueID() ,objSelector.getSelectorType());
	}

	public WebElement findWebelementByPartialSelector(WebElement parent, ObjectSelector objSelector, String iOSElementClass){
		List<WebElement> subset = collectWebElements(parent, iOSElementClass);
		return findWebelementByPartialSelector(subset, objSelector.getUinqueID() ,objSelector.getSelectorType());
	}

	private WebElement findWebelementByPartialSelector(List<WebElement> listOfWebElements, String target, String objSelector){ 
		if (listOfWebElements==null || listOfWebElements.size()==0){
			log("No matching WebElements found");
			return null;		
		} else {
			//			log(welm.size() + " elements Found");
		}
		String elmText = "";
		int counter = 0;
		for(WebElement elm : listOfWebElements){
			counter++;
			if(counter%10==0){
				log("Scaning " + counter + " of " + listOfWebElements.size());
			}
			switch (objSelector) {
			case "AccessibilityId":
				elmText = elm.getText();
				//				log(elmText);
				break;
			case "value":
				elmText = elm.getAttribute("value");
				break;
			case "name":
				elmText = elm.getAttribute("name");
				break;
			default:
				log("Not Supported");
				return null;
			}//end of switch

			if (elmText.contains(target)){
				log("found " + target +" in " + elmText);
				return elm;
			}
			//			log(elmText + " does not contain " + objSelector.getUinqueID());
		}
		log(target + " not found", true);
		return null;
	}


	public WebElement findWebelementBySelector(ObjectSelector selector){
		return omnidriver.findWebelementBySelector(selector);
	}// end of method

	public WebElement findWebelementBySelector(ObjectSelector parent, ObjectSelector selector){ // if you send a selector we asume you want to find a subview
		return findWebelementBySelector(findWebelementBySelector(parent), selector);
	}


	protected WebElement findDisplayedWebelement(ObjectSelector selector){
		List<WebElement> elements = collectWebElementsBySelector(selector);
		try {
			if(elements.size()==1){
				if(elements.get(0).isDisplayed()){
					return elements.get(0);
				}
			}else{
				for (WebElement target : elements){
					if(target.isDisplayed()){
						log("Found " +elements.size() +" Of " +selector.getDiscriptions());
						return target;
					}
				}
			}
			log("no Displayed element of " + selector.getDiscriptions() + " found of " + elements.size());
			return null;
		} catch (Exception e) {
			log("No elements found");
			return null;
		}
	}

	public WebElement findWebelementBySelector(WebElement parent ,ObjectSelector selector){
		//xpath , text ,className,ID, AccessibilityId
		String type = selector.getSelectorType();
		WebElement target = null;
		try {
			switch (type) {
			case "AccessibilityId":
				target  = parent.findElement(By.name(selector.getUinqueID()));
				break;
			case "ID":
				target  = parent.findElement(By.id(selector.getUinqueID()));
				break;
			case "className":
				target  = parent.findElement(By.className(selector.getUinqueID()));
				break;
			case "name":
				target  = parent.findElement(By.name(selector.getUinqueID())); //		log("name: " + obj.getAttribute("name"));
				break;
			case "xpath":
				target  = parent.findElement(By.xpath(selector.getUinqueID())); 
				break;
			default:
				log("Not Defined");
				break;
			}
		} catch (Exception e) {
			//			dumpAll(collentWebElements(iOSElementClass.UIATextField));
			//		log("Element not found");
		}
		return target;
	}// end of method

	public List<WebElement> collectWebElements(String iOSElementClass, boolean includeHidden){
		List<WebElement> list = new ArrayList<WebElement>();
		List<WebElement> rawList = omnidriver.findElementsByClassName(iOSElementClass);
		if(includeHidden){	
			return rawList;
		} else {
			for(WebElement elemnt : rawList){
				if (elemnt.isDisplayed()){
					list.add(elemnt);
				}
			}
		}
		log("found: " +rawList.size() + " returning Visibe: " +list.size());
		return list;
	}


	public List<WebElement> collectWebElements(String iOSElementClass){
		return collectWebElements(iOSElementClass, true);
	}

	public List<WebElement> collectWebElements(ObjectSelector parent, String iOSElementClass){
		return collectWebElements(findWebelementBySelector(parent), iOSElementClass);
	}

	public List<WebElement> collectWebElements(WebElement parent, String iOSElementClass){
		List<WebElement> list= new ArrayList<WebElement>();
		try {
			list =	parent.findElements(By.className(iOSElementClass));	
		} catch (Exception e) {
			log("Parent is null");
		}
		return list;
	} 
	public List<WebElement> collectWebElementsBySelector(ObjectSelector selectorName){
		return omnidriver.collectWebElementsBySelector(selectorName);
	}// end of method

	public Boolean enterTextWithKeyboard (Keys text){
		try {
			Keyboard keyboard = omnidriver.getKeyboard();
			keyboard.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void hideKeyboard(){
		omnidriver.hideKeyboard();
	}

	public Boolean enterTextWithKeyboard (String text){
		try {
			Keyboard keyboard = omnidriver.getKeyboard();
			keyboard.sendKeys(text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void closeAll(){
		setMoble(false);
		closeApp();	
		setMoble(true);
		closeApp();
	}
	public HashMap<String, WebElement> creatTextHash(List<WebElement> webObjects, boolean includeHidden){
		HashMap<String, WebElement> textAndLoc = new HashMap<String, WebElement>();
		for (WebElement elm : webObjects){
			if (!includeHidden && !elm.isDisplayed()){
				continue;
			}
			String text = elm.getText();
			if (text.length()>0){
				textAndLoc.put(text, elm);
			}
		}
		return textAndLoc;
	}

	//	public void enterText(String text, ObjectSelector target,boolean clearFirst){
	//		enterText(text, findWebelementBySelector(target),clearFirst);
	//	}
	//
	//	public void enterText(String text, ObjectSelector target){
	//		enterText(text, findWebelementBySelector(target),false);
	//	}

	public void enterText(String text, ObjectSelector target,boolean clearFirst){
		enterText(text, findDisplayedWebelement(target),clearFirst);
	}

	public void enterText(String text, ObjectSelector target){
		enterText(text, findDisplayedWebelement(target),false);
	}

	public void enterText(String text, WebElement target,boolean clearFirst){
		log("entering text: " + text);
		if(!singleTapWebElement(target,true)){
			log("Text field not found",true);
			return;
		}
		if(clearFirst){
			target.clear();
		}
		//		if(omnidriver.isMobile() && text.contains("@") && omnidriver.isiOSdevice()){ //if its email we need to check to see if the contact popup happens
		//			int waitTime = getWaitTime();
		//			setWaitTime(1);
		//			enterTextWithKeyboard("a");
		//			//			if(doesWebElementExist(ShareScreen.DontAllowButton)){
		//			//				singleTapWebElement(ShareScreen.DontAllowButton);
		//			//			}
		//			pressDelete();
		//			setWaitTime(waitTime);
		//		}
		enterTextWithKeyboard(text);
		if(!omnidriver.isMobile()){
			changeField(target);
		}
	}

	public void pressReturn(){
		Keys enter =  Keys.RETURN;
		enterTextWithKeyboard(enter);
	}

	public void pressDelete(){
		Keys delete = Keys.DELETE;
		enterTextWithKeyboard(delete);
	}

	public boolean evaluateTest(boolean result , String testName){
		return evaluateTest(result, testName, true);
	}

	public boolean evaluateTest(boolean result , String testName , boolean withScreenShot){
		if (result){
			log("PASS: " + testName,withScreenShot); //we might not want a screen shot if nothing changes between evaluate's.
		} else {
			log("FAIL: " + testName,true); //if we fail we ALWAYS want a screenshot. 
			assertTrue(testName, result);
		}
		return result;
	}
	public void skipTest(String reason){
		throw new SkipException(reason);
	}
	public boolean doesWebElementExist(ObjectSelector selector){
		return doesWebElementExist(selector, false, false, "only used if partalText is true");
	}

	public boolean doesWebElementExist(ObjectSelector selector, boolean includeHidden){
		return doesWebElementExist(selector, includeHidden, false, "only used if partalText is true");
	}
	public boolean doesWebElementExist(ObjectSelector selector , boolean includeHidden, boolean partalText , String iOSElementClass){
		if (partalText){
			return doesWebElementExist(findWebelementByPartialSelector(selector, iOSElementClass),includeHidden);
		}
		if(includeHidden){
			return doesWebElementExist(findWebelementBySelector(selector), includeHidden);
		}
		return doesWebElementExist(findDisplayedWebelement(selector),includeHidden);
	}
	public boolean doesWebElementExist(WebElement webElement , boolean includeHidden){
		if (webElement==null){
			return false;
		}else if (includeHidden){
			return true;
		}
		if (!webElement.isDisplayed()){
			log("Object '"+ webElement.getText() +  "' hidden",true); 
			//		dumpWebElement(webElement, true);
		}
		return webElement.isDisplayed();
	}
	public void swipeInDirecton(direction dir){
		swipeInDirecton(dir,1);
	}
	public void swipeInDirecton(direction dir, double speed){
		Point lowerRight = new Point(screenSize.width,screenSize.height);
		Point start = new Point (lowerRight.x/2,lowerRight.y/2);
		Point end = new Point(lowerRight.x/2,lowerRight.y/2);

		switch (dir) {
		case up:
			start.y=  (int) (lowerRight.y *.75);
			end.y = 1;
			break;
		case down:
			start.y=  (int) (lowerRight.y *.25);
			end.y = lowerRight.y-1;
			break;
		case left:
			start.x=  (int) (lowerRight.x *.95);
			end.x = 1;
			break;
		case right:
			start.x=  (int) (lowerRight.x *.05);
			end.x = lowerRight.x-1;
			break;
		default:
			break;
		}
		excuteSwipe(start, end, speed);
	}


	public void swipeInDirecton(WebElement object, direction dir, double speed){
		Dimension objectSize = object.getSize();
		Point loc = object.getLocation();
		Point lowerRight = new Point(objectSize.width  ,objectSize.height );
		Point start = new Point(lowerRight.x/2+ loc.x,lowerRight.y/2+ loc.y);
		Point end =   new Point(lowerRight.x/2+ loc.x,lowerRight.y/2+ loc.y);

		switch (dir) {
		case up:
			start.y=  (int) (lowerRight.y *.75);
			end.y = 1;
			break;
		case down:
			start.y=  (int) (lowerRight.y *.25);
			end.y = lowerRight.y-1;
			break;
		case left:
			start.x=  (int) (lowerRight.x *.75);
			end.x = 1;
			break;
		case right:
			start.x=  (int) (lowerRight.x *.25);
			end.x = lowerRight.x-1;
			break;
		default:
			break;
		}
		excuteSwipe(start, end, speed);
	}
	private void excuteSwipe(Point start, Point end, double speedMulti){
		double distance =  Math.sqrt((Math.pow((start.x -end.x),2) + Math.pow((start.y - end.y),2)));
		int speed = (int) (distance * speedMulti);
		if (speed < 500){
			log("Speed to fast, slowing down to .5 seconds");
			speed = 500;
		}
		log("swipeing from " + start.toString() + " To " + end.toString() + " With speed " + speed);
		omnidriver.swipe(start, end, speed);
	}
	public int returnRandom(int max){
		Random dice = new Random();
		return dice.nextInt(max) +1; //return 1 to max
	}

	public boolean isTextInList (String target , List<String> lists){ // usefull for checking partal text
		for (String list : lists){
			if (list.contains(target)){
				//							log("Text " + target + " Found in: "+ list);
				return true;
			} else {
				//				log("no Match " + list + " and " + target);
			}
		}
		log("no Match " + target);
		return false;
	}

	public boolean verifyScreenText(List<String> screenText, String[] referenceText){
		boolean allTextFound = true;
		if (screenText.size()==0){
			return false;
		}
		for(String ref  : referenceText){
			if(screenText.contains(ref)){
				log("Found: " + ref);
			} else {
				allTextFound = false;
				log("Not Found: " + ref);
			}
		}
		return allTextFound;
	}

	public boolean isWebElementEnabled (WebElement element){
		try {
			return element.isEnabled();
		} catch (Exception e) {
			log("element not found");
			return false;
		}
	}
	public boolean isWebElementDisplayed (WebElement element){
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			log("element not found");
			return false;
		}
	}

	public boolean isWebElementEnabled (ObjectSelector selector){
		return isWebElementEnabled(findWebelementBySelector(selector));
	}
	public boolean isWebElementDisplayed (ObjectSelector selector){
		return isWebElementDisplayed(findWebelementBySelector(selector)); 
	}

	public boolean waitForElement(ObjectSelector parent, ObjectSelector child, int seconds,boolean allowHidden){
		for (int i =1 ;i<=seconds; i++){
			sleep(1);
			if (i%10==0){
				log(i+" waiting for "+ child.getDiscriptions(), true);
			}
			if (doesWebElementExist(findWebelementBySelector(parent,child),allowHidden)){
				log(child.getDiscriptions() + " Found");
				return true;
			}
		}
		log(child.getDiscriptions() + " Not Found");
		return false;
	}


	public boolean waitForElement(ObjectSelector selector,int seconds,boolean allowHidden){
		int wtime=	getWaitTime();
		setWaitTime(1);
		for (int i =1 ;i<=seconds; i++){
			sleep(1);
			if (i%10==0){
				log(i+" waiting for "+ selector.getDiscriptions(), true);
			}
			if (doesWebElementExist(findWebelementBySelector(selector),allowHidden)){
				log(selector.getDiscriptions() + " Found");
				setWaitTime(wtime);
				return true;
			}
		}
		log(selector.getDiscriptions() + " Not Found");
		setWaitTime(wtime);
		return false;
	}

	public void closeApp(){
		omnidriver.closeApp();
	}
	public void launchApp() {
		omnidriver.launchApp();	
	}
	public String getPageSource(){
		String	html = omnidriver.getPageSource();
		if (html == null){
			log("Error no webdriver found");
			return html; // return early so we don't try to invoke .equals on a null.
		}
		if (html.equals("HTML only")){
			log("use only with web testing");
		}
		return html;
	}
	public void createArray(List<String> texts){
		for(String text :texts ){
			if(text.contains("\"")){
				System.out.println();	
			}
			System.out.print("\""+text+"\",");	
		}
		System.out.println(" ");
	}

	public long getUniqueNumber(){
		Date now = new Date(); 
		long intTime = now.getTime();
		return intTime%31556926; // number of seconds in a year.
	}
	public void goBack(){
		omnidriver.goBack();
	}
	public void refreshWebpage(){
		if(omnidriver.refreshWebPage()){
			sleep(5);
			log("loading page");
		}else{
			log("Refresh faild");
		}
	}
	public void changeField(ObjectSelector selector){
		changeField(findDisplayedWebelement(selector));
	}

	public void changeField(WebElement element){
		omnidriver.changeField(element);
	}
	public void quit(){
omnidriver.quit();
	}
	public void deleteAllGmail(){

	}

}// end of class
