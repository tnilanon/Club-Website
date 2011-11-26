package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class AddBookmarkForm extends FormBean {
	private String url		= null;
	private String comment	= null;
	
	public String getUrl()		{ return url;		}
	public String getComment()	{ return comment;	}
	
	public void setUrl(String s) {
		url = trimAndConvert(s, "<>&\'\"");
	}
	
	public void setComment(String s) {
		if (s == null)
			comment = "";
		else
			comment = trimAndConvert(s, "<>&\'\"");
	}
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		int l;
		if (url == null || (l = url.length()) == 0) {
			errors.add("URL cannot be empty");
		} else {
			if (url.indexOf('.') == -1) {
				errors.add("URL should contain a period");
			} else if (url.charAt(0) == '.' || url.charAt(l-1) == '.') {
				errors.add("URL should not have a period as the first or last character");
			}
		}
		
		return errors;
	}
}

