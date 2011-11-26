package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class RegisterForm extends FormBean {
	private String emailAddress			= null;
	private String password				= null;
	private String confirmPassword		= null;
	private String firstName			= null;
	private String lastName				= null;
	
	public String getEmailAddress()		{ return emailAddress;	}
	public String getPassword()			{ return password;		}
	public String getConfirmPassword()	{ return confirmPassword;	}
	public String getFirstName()		{ return firstName;			}
	public String getLastName()			{ return lastName;			}
	
	public void setEmailAddress(String s)	{ emailAddress = trimAndConvert(s, "<>&\'\"");	}
	public void setPassword(String s)			{ password = s.trim();						}
	public void setConfirmPassword(String s)	{ confirmPassword = s.trim();				}
	public void setFirstName(String s)			{ firstName = trimAndConvert(s, "<>&\'\"");	}
	public void setLastName(String s)			{ lastName = trimAndConvert(s, "<>&\'\"");	}
	
	// This is meant to be used in conjunction with LoginForm
	// This method will only validate the parts not validated by LoginForm
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (confirmPassword == null || confirmPassword.length() == 0 || ! confirmPassword.equals(password)) {
			errors.add("Passwords do not match");
		}
		
		if (firstName == null || firstName.length() == 0) {
			errors.add("First name cannot be empty");
		}
		
		if (lastName == null || lastName.length() == 0) {
			errors.add("Last name cannot be empty");
		}
		
		return errors;
	}
}

