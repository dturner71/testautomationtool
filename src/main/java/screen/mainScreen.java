package screen;

import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;

public final class mainScreen{
	//public static ObjectSelector name = new ObjectSelector(SelectorType.name, "", "");
	//public static ObjectSelector nextButton = new ObjectSelector(SelectorType.name, "imageViewProfilePic", "NEXT button");
	public static ObjectSelector profilePic = new ObjectSelector(SelectorType.ID, "imageViewProfilePic", "Profile Pic");
}