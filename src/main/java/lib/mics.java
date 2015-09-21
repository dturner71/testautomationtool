package lib;


import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;

public class mics extends AbraLibrary{

	public void enterPinPad(String key){
		  ObjectSelector presskey = new ObjectSelector(SelectorType.name, key, "key: " +key);
		  singleTapWebElement(presskey);
	}
	
}// end of class
