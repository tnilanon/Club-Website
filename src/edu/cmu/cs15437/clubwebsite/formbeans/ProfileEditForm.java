package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class ProfileEditForm extends FormBean {
	private String emailAddress			= null;
	private String userName				= null;
	private String firstName			= null;
	private String lastName				= null;
	private String sex					= null;
	private String button				= null;
	
	public String getEmailAddress()		{ return emailAddress;	}
	public String getUserName()			{ return userName;		}
	public String getFirstName()		{ return firstName;		}
	public String getLastName()			{ return lastName;		}
	public String getSex()				{ return sex;			}
	public String getButton()			{ return button;		}
	
	public void setEmailAddress(String s)	{ emailAddress = trimAndConvert(s, "<>&\'\"");	}
	public void setUserName(String s)		{ userName = trimAndConvert(s, "<>&\'\"");	}
	public void setFirstName(String s)		{ firstName = trimAndConvert(s, "<>&\'\"");	}
	public void setLastName(String s)		{ lastName = trimAndConvert(s, "<>&\'\"");	}
	public void setSex(String s)			{ sex = s;									}
	public void setButton(String s)			{ button = s;								}
	

	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (firstName == null || firstName.length() == 0) {
			errors.add("First name cannot be empty");
		}
		
		if (lastName == null || lastName.length() == 0) {
			errors.add("Last name cannot be empty");
		}
		
		if (userName == null || userName.length() == 0) {
			errors.add("Last name cannot be empty");
		}
		
		if(sex == null || sex.length() == 0) {
			errors.add("Sex must be selcted");
		}
		
		if (button == null || ! (button.equals("Submit Changes") || button.equals("Change Password"))) {
			errors.add("Submit changes to proceed");
		}
		
		return errors;
	}
}

