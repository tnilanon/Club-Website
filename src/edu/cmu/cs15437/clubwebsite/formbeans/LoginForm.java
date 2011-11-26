package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class LoginForm extends FormBean {
	private String emailAddress		= null;
	private String password			= null;
	private String button			= null;
	
	public String getEmailAddress()	{ return emailAddress;	}
	public String getPassword()		{ return password;		}
	public String getButton()		{ return button;		}
	
	public void setEmailAddress(String s)	{ emailAddress = trimAndConvert(s, "<>&\'\"");	}
	public void setPassword(String s)		{ password = s.trim();							}
	public void setButton(String s)			{ button = s;									}
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		int l;
		if (emailAddress == null || (l = emailAddress.length()) == 0) {
			errors.add("Email address cannot be empty");
		} else {
			int a = emailAddress.indexOf('@');
			int temp = -1;
			boolean p = false;
			if (a == -1 || a != emailAddress.lastIndexOf('@')) {
				errors.add("Email address must contain exactly one @");
			}
			while ((temp = emailAddress.indexOf('.', temp+1)) != -1) {
				if (temp != 0 && temp != l-1 && temp != a-1 && temp != a+1) {
					p = true;
					break;
				}
			}
			if (! p) {
				errors.add("Email address must contain at least one period nonadjacent and not the first or last character");
			}
		}
		
		if (password == null || password.length() == 0) {
			errors.add("Password cannot be empty");
		}
		
		if (button == null || ! (button.equals("Login") || button.equals("Register") || button.equals("Complete Registration"))) {
			errors.add("Login or register to proceed");
		}
		
		return errors;
	}
}

