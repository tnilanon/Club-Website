package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class ProfileEditForm extends FormBean {
	private String emailAddress			= null;
	private String password				= null;
	private String confirmPassword		= null;
	private String userName				= null;
	private String firstName			= null;
	private String lastName				= null;
	private String sex					= null;
	
	public String getEmailAddress()		{ return emailAddress;	}
	public String getPassword()			{ return password;		}
	public String getConfirmPassword()	{ return confirmPassword;	}
	public String getUserName()			{ return userName;		}
	public String getFirstName()		{ return firstName;		}
	public String getLastName()			{ return lastName;		}
	public String getSex()				{ return sex;			}
	
	public void setEmailAddress(String s)	{ emailAddress = trimAndConvert(s, "<>&\'\"");	}
	public void setPassword(String s)		{ password = s.trim();						}
	public void setConfirmPassword(String s)	{ confirmPassword = s.trim();				}
	public void setUserName(String s)		{ userName = trimAndConvert(s, "<>&\'\"");	}
	public void setFirstName(String s)		{ firstName = trimAndConvert(s, "<>&\'\"");	}
	public void setLastName(String s)		{ lastName = trimAndConvert(s, "<>&\'\"");	}
	public void setSex(String s)			{ sex = s;									}
	

	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		
		if (password == null || password.length() == 0) {
			errors.add("Password cannot be empty");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0 || ! confirmPassword.equals(password)) {
			errors.add("Passwords do not match");
		}
		
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
		
		return errors;
	}
}

