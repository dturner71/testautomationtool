package lib;

import org.openqa.selenium.WebElement;

import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;
import screen.numberProxy;

public class AbraLibrary extends Library{

	public void enterPinPad(String key){
		ObjectSelector presskey = new ObjectSelector(SelectorType.name, key, "key: " +key);
		singleTapWebElement(presskey);
	}

	public void loginNumberProxy(){
		setMoble(false);
		navagateToWebpage("https://numberproxy.com/login.php");
		enterText("david@goabra.com", numberProxy.email);
		enterText("nmbiey2d", numberProxy.password);
		singleTapWebElement(numberProxy.loginButton);
		sleep(3);
		navagateToWebpage("https://numberproxy.com/inbox.php");
		setMoble(true);
	}
	public void emptyInbox(){
		setMoble(false);
		for(int i=0 ; i<10 ; i++){
			if(!doesWebElementExist(numberProxy.checkBoxOne)){
				break;
			}
			singleTapWebElement(numberProxy.checkAllButton);
			singleTapWebElement(numberProxy.deleteAllSelected);
		}
		setMoble(true);
	}
	public String getCode(){
		setMoble(false);
		singleTapWebElement(numberProxy.readMail);
		WebElement ele = findWebelementBySelector(numberProxy.unreadEmail);
		String phrase = getText(ele, SelectorType.AccessibilityId);
		String[] words = phrase.split(" ");
		String code = words[words.length-1];
		String numbers = code.replace(".", "");
		setMoble(true);
		return numbers;
	}
}// end of class
