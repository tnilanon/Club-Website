package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

public class VideoForm extends FormBean {
	private String button 	= null;
	private String videoID 	= null;
	
	public String getButton()		{ return button;	}
	public String getVideoID()		{ return videoID;	}
	
	public void setButton(String s)		{ button = s;	}
	public void setVideoID(String s)	{ videoID = s;	}
			
}

