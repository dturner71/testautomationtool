package screen;

import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;

public final class countryScreen{
//public static ObjectSelector name = new ObjectSelector(SelectorType.name, "UNITED STATES OF AMERICA", "Part supported");

public static ObjectSelector countrySelectScreen = new ObjectSelector(SelectorType.name, "Your Home Country", "Country select screen");	
public static ObjectSelector nextButton = new ObjectSelector(SelectorType.name, "NEXT", "NEXT button");
public static ObjectSelector countryButton = new ObjectSelector(SelectorType.ID, "buttonCountry", "Country Button");
public static ObjectSelector infoText= new ObjectSelector(SelectorType.ID, "txt_info_text", "Info Text");
public static ObjectSelector emailTextField = new ObjectSelector(SelectorType.name, "ENTER EMAIL", "Email text field");
public static ObjectSelector getNotified = new ObjectSelector(SelectorType.name, "Get Notified", "Title Text");
public static ObjectSelector searchBox = new ObjectSelector(SelectorType.name, "Search", "country search box");
public static ObjectSelector signUpTitle = new ObjectSelector(SelectorType.name, "Sign Up", "Sign up page title");

public static ObjectSelector Supported = new ObjectSelector(SelectorType.name, "Philippines", "Supported Country");
public static ObjectSelector Partially = new ObjectSelector(SelectorType.name, "United States (USA)", "Partially supported");
public static ObjectSelector unsuported = new ObjectSelector(SelectorType.name, "Afghanistan", "Unsuported country Afghanistan");

public static ObjectSelector setUpAbraButton = new ObjectSelector(SelectorType.name, "SET UP ABRA", "Set up Button");
public static ObjectSelector getNotifiedButton = new ObjectSelector(SelectorType.name, "GET NOTIFIED", "Get Notified");

public static ObjectSelector usa = new ObjectSelector(SelectorType.name, "UNITED STATES OF AMERICA", "USA title");
}