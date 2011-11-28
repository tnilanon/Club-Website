package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

public class AdmitMemberForm extends FormBean {
	private String emailAddress			= null;
	private String memberType			= null;

	
	public String getEmailAddress()		{ return emailAddress;	}
	public String getMemberType()		{ return memberType;	}

	
	public void setEmailAddress(String s)	{ emailAddress = trimAndConvert(s, "<>&\'\"");	}
	public void setMemberType(String s)		{ memberType = s;	}
	
	
}

