package edu.cmu.cs15437.clubwebsite.databeans;

public class VideoBean implements Comparable< VideoBean > {
	private int videoId					= -1;
	private int ownerId					= -1;
	private int accessLevel				= -1;
	private String link					= null;
	private String description			= null;
	private Date date					= null;
	
	public VideoBean(int videoId) {
		this.videoId = videoId;
	}
	
	public int getVideoId()			{ return videoId;		}
	public int getOwnerId()			{ return ownerId;		}
	public int getAccessLevel()		{ return accessLevel;	}
	public String getLink()			{ return link;			}
	public String getDescription()	{ return description;	}
	public Date getDate()			{ return date;			}
	
	public void setOwnerId(int i)			{ ownerId = i;		}
	public void setAccessLevel(int i)		{ accessLevel = i;	}
	public void setLink(String s)			{ link = s;			}
	public void setDescription(String s)	{ description = s;	}
	public void setDate(Date d)				{ date = d;			}
	
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

