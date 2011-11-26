package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class ClickLinkForm extends FormBean {
	private String bookmarkIdStr	= null;
	private String listType			= null;
	
	public String getBookmarkIdStr()	{ return bookmarkIdStr;	}
	public String getListType()			{ return listType;		}
	
	public void setBookmarkIdStr(String s)	{ bookmarkIdStr = s;	}
	public void setListType(String s)		{ listType = s;			}
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (bookmarkIdStr == null || bookmarkIdStr.length() == 0 || listType == null || !(listType.equals("myBookmarks") || listType.equals("allBookmarks") || listType.equals("top10Bookmarks"))) {
			errors.add("Please try again; it seems the webpage you received has been tampered");
		}
		
		return errors;
	}
}

