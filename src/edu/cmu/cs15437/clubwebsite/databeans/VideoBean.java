package edu.cmu.cs15437.clubwebsite.databeans;

import java.util.Date;

public class VideoBean implements Comparable< VideoBean > {
	private int videoId					= -1;
	private int ownerId					= -1;
	private int accessLevel				= -1;
	private String link					= null;
	private String description			= null;
	private long dateValue				= -1;
	
	public VideoBean(int videoId) {
		this.videoId = videoId;
	}
	
	public int getVideoId()			{ return videoId;		}
	public int getOwnerId()			{ return ownerId;		}
	public int getAccessLevel()		{ return accessLevel;	}
	public String getLink()			{ return link;			}
	public String getDescription()	{ return description;	}
	public long getDateValue()		{ return dateValue;		}
	
	public void setOwnerId(int i)			{ ownerId = i;		}
	public void setAccessLevel(int i)		{ accessLevel = i;	}
	public void setLink(String s)			{ link = s;			}
	public void setDescription(String s)	{ description = s;	}
	public void setDateValue(long l)		{ dateValue = l;	}
	
	public int compareTo(VideoBean other) {
		// Order by recency
		return other.videoId - videoId;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof VideoBean) {
			VideoBean other = (VideoBean) obj;
			return videoId == other.videoId;
		}
		return false;
	}
	
	public String toString() {
		return "Video( " + String.valueOf(videoId) + ", Owner( " + ownerId + " ), AccessLevel( " + String.valueOf(accessLevel) + " ) )";
	}
}

