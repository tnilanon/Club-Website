package edu.cmu.cs15437.clubwebsite.formbeans;

import org.mybeans.form.FormBean;

import java.util.*;
import java.util.regex.*;

public class EditVideoForm extends FormBean {
	private String description	= null;
	private String radio		= null;
	private String videoId		= null;
	
	public String getDescription()	{ return description;	}
	public String getRadio()		{ return radio;			}
	public String getVideoId()		{ return videoId;		}

	public void setComment(String s)	{ description = trimAndConvert(s, "<>&\'\"");	}
	public void setRadio(String s)		{ radio = s;		}
	public void setVideoId(String s)	{ videoId = s;		}
	
	public List< String > getValidationErrors() {
		List< String > errors = new ArrayList< String >();
		
		if (radio == null || radio.length() == 0) {
			errors.add("Permission level must be set");
		}
		if (videoId == null || videoId.length() == 0) {
			errors.add("Video id is not present");
		}
		
		return errors;
	}
}