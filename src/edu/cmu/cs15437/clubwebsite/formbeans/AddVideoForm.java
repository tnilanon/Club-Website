package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;
import java.util.regex.*;

public class AddVideoForm extends FormBean {
	private String link			= null;
	private String description	= null;
	
	private static Pattern pattern = Pattern.compile("v=([^&]+)(?:&.*)?$");
	
	public String getLink()			{ return link;			}
	public String getDescription()	{ return description;	}
	
	public void setLink(String s) {
		// Extract video id
		System.out.println(s);
		Matcher m = pattern.matcher(s);
		if (m.find())
			link = m.group(1);
		else
			link = "";
		System.out.println(link);
	}
	
	public void setComment(String s) {
		if (s == null)
			description = "";
		else
			description = trimAndConvert(s, "<>&\'\":");
	}
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (link == null || link.length() == 0) {
			errors.add("Video link cannot be empty");
		}
		
		return errors;
	}
}