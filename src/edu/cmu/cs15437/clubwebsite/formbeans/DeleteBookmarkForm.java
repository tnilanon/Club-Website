package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class DeleteBookmarkForm extends FormBean {
	private String bookmarkIdStr = null;
	
	public String getBookmarkIdStr() { return bookmarkIdStr; }
	
	public void setBookmarkIdStr(String s) { bookmarkIdStr = s; }
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (bookmarkIdStr == null || bookmarkIdStr.length() == 0) {
			errors.add("Please try again; it seems the webpage you received has been tampered");
		}
		
		return errors;
	}
}

