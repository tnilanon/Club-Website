package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class AddVideoForm extends FormBean {
	private String link			= null;
	private String description	= null;
	
	public String getLink()			{ return link;			}
	public String getDescription()	{ return description;	}
	
	public void setLink(String s) {
		link = trimAndConvert(s, "<>&\'\"");
	}
	
	public void setComment(String s) {
		if (s == null)
			description = "";
		else
			description = trimAndConvert(s, "<>&\'\"");
	}
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (link == null || link.length() == 0) {
			errors.add("Video link cannot be empty");
		}
		
		return errors;
	}
}