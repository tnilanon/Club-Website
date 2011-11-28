package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;

public class VideoForm extends FormBean {
	private String button 	= null;
	private String videoId 	= null;
	
	public String getButton()		{ return button;	}
	public String getVideoId()		{ return videoId;	}
	
	public void setButton(String s)		{ button = s;	}
	public void setVideoId(String s)	{ videoId = s;	}
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (button == null || button.length() == 0) {
			errors.add("Please use the given buttons");
		}
		if (videoId == null || videoId.length() == 0) {
			errors.add("Video id must be non-empty");
		}
		
		return errors;
	}
}

