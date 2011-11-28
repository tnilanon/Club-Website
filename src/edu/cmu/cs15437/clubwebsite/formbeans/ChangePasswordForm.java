package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class ChangePasswordForm extends FormBean {
	private String password			= null;
	private String confirmPassword	= null;
	
	public String getPassword()			{ return password;		}
	public String getConfirmPassword()	{ return confirmPassword;	}
	
	public void setPassword(String s)		{ password = trimAndConvert(s, "<>&\'\"");	}
	public void setConfirmPassword(String s)	{ confirmPassword = trimAndConvert(s, "<>&\'\"");	}
	

	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (password == null || password.length() == 0) {
			errors.add("Password cannot be empty");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0 || ! confirmPassword.equals(password)) {
			errors.add("Passwords do not match");
		}
		
		return errors;
	}
}

