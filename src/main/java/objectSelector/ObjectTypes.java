package objectSelector;

public class ObjectTypes {
	/* UIAButton
	 * UIAStaticText
	 * UIATextField
	 * UIASecureTextField
	 */
	//	public enum ElementTypes {UIAButton, UIAStaticText, UIATextField, UIASecureTextField, LinearLayout, view, EditText};

	public interface iOSElementClass {
		String UIAButton = "UIAButton";
		String UIAStaticText = "UIAStaticText";
		String UIATextField = "UIATextField";  
		String UIASecureTextField = "UIASecureTextField";
		String UIAImage = "UIAImage";
		String UIANavigationBar = "UIANavigationBar";
		String UIAElement = "UIAElement";
		String UIATableGroup = "UIATableGroup";
		String UIAKey = "UIAKey";
		String UIATableView ="UIATableView";
		String UIATableCell = "UIATableCell";
		String UIATextView = "UIATextView";
		String UIASwitch = "UIASwitch";
		String UIACollectionCell = "UIACollectionCell";
		String UIAScrollView = "UIAScrollView";
		String UIAAlert = "UIAAlert";
	}

	public interface androidWidget{
		String TextView  = "android.widget.TextView";
	}
	
	public interface SelectorType {
		String xpath =  "xpath";
		String value = "value";
		String className = "className";
		String ID = "ID";
		String AccessibilityId = "AccessibilityId";
		String name = "name";
		String label = "label";
		String CssSelector = "CssSelector";
	}
	public interface WebTags {
		String td =  "td";
		String a="a";
		String span="span";
	}
}
