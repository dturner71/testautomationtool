package screen;

import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;

public final class numberProxy{
//public static ObjectSelector deleteAllSelected = new ObjectSelector(SelectorType.name, "status unread", "unreadEmail");

	public static ObjectSelector email = new ObjectSelector(SelectorType.name, "login_email", "Email name");
	public static ObjectSelector password = new ObjectSelector(SelectorType.name, "login_password", "Email name");
	public static ObjectSelector loginButton = new ObjectSelector(SelectorType.ID, "btn_login", "Login Button");
	public static ObjectSelector checkAllButton = new ObjectSelector(SelectorType.ID, "checkAll", "Checkall Button");
	public static ObjectSelector deleteAllSelected = new ObjectSelector(SelectorType.ID, "deleteAllSelected", "delete All Selected Button");
	public static ObjectSelector checkBoxOne = new ObjectSelector(SelectorType.name, "checkbox[]", "mesg check box Button");
	public static ObjectSelector readMail = new ObjectSelector(SelectorType.className, "ReadSelectedPM", "unreadEmail");
	public static ObjectSelector unreadEmail = new ObjectSelector(SelectorType.ID, "read_pm_message", "unreadEmail");
	
}