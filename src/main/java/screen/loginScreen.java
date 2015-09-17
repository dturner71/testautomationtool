package screen;

import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;

public final class loginScreen{
//public static ObjectSelector name = new ObjectSelector(SelectorType.name, "", "");

public static ObjectSelector nextButton = new ObjectSelector(SelectorType.name, "NEXT", "NEXT button");
public static ObjectSelector getStarted = new ObjectSelector(SelectorType.name, "GET STARTED", "GET STARTED button");	


public static ObjectSelector pageOne = new ObjectSelector(SelectorType.name, "1. ADD MONEY", "Page 1 text");
public static ObjectSelector pageTwo = new ObjectSelector(SelectorType.name, "2. SEND MONEY", "Page 2 text");
public static ObjectSelector pageThree = new ObjectSelector(SelectorType.name, "3. WITHDRAW MONEY", "Page 3 text");
public static ObjectSelector countrySelectScreen = new ObjectSelector(SelectorType.name, "Your Home Country", "Country select screen");

public static ObjectSelector countryButton = new ObjectSelector(SelectorType.ID, "buttonCountry", "Country Button");

//public static ObjectSelector name = new ObjectSelector(SelectorType.name, "", "");
//public static ObjectSelector name = new ObjectSelector(SelectorType.name, "", "");
//public static ObjectSelector name = new ObjectSelector(SelectorType.name, "", "");
}