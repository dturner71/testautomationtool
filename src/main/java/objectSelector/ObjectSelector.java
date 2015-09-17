package objectSelector;

import lib.Library;


public class ObjectSelector extends Library {
	private String selectorType;
	private String androidUinqueID,iOSUinqueID,discription;

	//overloading the selector for android only apps.
	public ObjectSelector (String SelectorType , String sharedUinqueID, String discription ){
		this.selectorType = SelectorType;
		this.androidUinqueID = sharedUinqueID;
		this.iOSUinqueID = sharedUinqueID; 
		this.discription = discription;
	}
	public ObjectSelector (String selectorType , String androidUinqueID, String iOSUinqueID, String discription ) {
		this.selectorType = selectorType;
		this.androidUinqueID = androidUinqueID;
		this.iOSUinqueID = iOSUinqueID;
		this.discription = discription;
	}
	public String getSelectorType(){
		return this.selectorType;
	}
	
	public String getWebClass(){ // for use on webbase test. find by text
		return this.androidUinqueID;
	}
	public String getWebText(){
		return this.iOSUinqueID;
	}
		
	public String getUinqueID(){
		if(isiOSDevice()){
			return	this.iOSUinqueID;
		} else {
			return this.androidUinqueID;
		}
	}
	public String getDiscriptions(){
		return this.discription;
	}
}
