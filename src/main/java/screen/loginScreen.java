package screen;

import objectSelector.ObjectSelector;
import objectSelector.ObjectTypes.SelectorType;

public final class loginScreen{
//public static ObjectSelector backButton = new ObjectSelector(SelectorType.id, "BACK", "Back Button");

public static ObjectSelector nextButton = new ObjectSelector(SelectorType.name, "NEXT", "NEXT button");
public static ObjectSelector getStarted = new ObjectSelector(SelectorType.name, "GET STARTED", "GET STARTED button");	
public static ObjectSelector pageOne = new ObjectSelector(SelectorType.name, "1. ADD MONEY", "Page 1 text");
public static ObjectSelector pageTwo = new ObjectSelector(SelectorType.name, "2. SEND MONEY", "Page 2 text");
public static ObjectSelector pageThree = new ObjectSelector(SelectorType.name, "3. WITHDRAW MONEY", "Page 3 text");


public static ObjectSelector firstNameText = new ObjectSelector(SelectorType.ID, "editTextFirstName", "First name text field");
public static ObjectSelector lastNameText = new ObjectSelector(SelectorType.ID, "editTextLastName", "Last name text field");
public static ObjectSelector emailNameText = new ObjectSelector(SelectorType.ID, "editTextEmail", "Email text field");
public static ObjectSelector pinPageOneText = new ObjectSelector(SelectorType.name, "Choose your 4 digit PIN", "Pin page text");
public static ObjectSelector pinPageTwoText = new ObjectSelector(SelectorType.name, "Confirm your 4 digit PIN", "Pin page text");
public static ObjectSelector phoneScreen = new ObjectSelector(SelectorType.name, "Sign Up", "Phone Screen");
public static ObjectSelector phoneNumberTxt = new ObjectSelector(SelectorType.ID, "editTextPhoneNum", "Phone Number text field");
public static ObjectSelector enterCode = new ObjectSelector(SelectorType.name, "ENTER CODE", "phone confromation text field");
public static ObjectSelector verifyCode = new ObjectSelector(SelectorType.ID, "editTextVerifyCode", "code text field");

public static ObjectSelector backButton = new ObjectSelector(SelectorType.name, "BACK", "Back Button");

public static ObjectSelector pin1 = new ObjectSelector(SelectorType.ID, "textViewPIN0", "Fist pin box");
public static ObjectSelector pin2 = new ObjectSelector(SelectorType.ID, "textViewPIN1", "Second pin box");
public static ObjectSelector pin3 = new ObjectSelector(SelectorType.ID, "textViewPIN2", "Third pin box");
public static ObjectSelector pin4 = new ObjectSelector(SelectorType.ID, "textViewPIN3", "Forth pin box");
}