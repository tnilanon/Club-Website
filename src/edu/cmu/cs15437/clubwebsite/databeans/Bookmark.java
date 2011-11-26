package edu.cmu.cs15437.clubwebsite.databeans;

public class Bookmark implements Comparable< Bookmark > {
	private int id					= -1;
	private String userEmailAddress	= null;
	private String url				= null;
	private String comment			= null;
	private int click				= -1;
	
	public int getId()					{ return id;				}
	public String getUserEmailAddress()	{ return userEmailAddress;	}
	public String getUrl()				{ return url;				}
	public String getComment()			{ return comment;			}
	public int getClick()				{ return click;				}
	
	public void setId(int i)					{ id = i;				}
	public void setUserEmailAddress(String s)	{ userEmailAddress = s;	}
	public void setUrl(String s)				{ url = s;				}
	public void setComment(String s)			{ comment = s;			}
	public void setClick(int i)					{ click = i;			}
	
	public int compareTo(Bookmark other) {
		int c;
		// Order by popularity first, then by recency
		c = other.click - click;
		if (c != 0) return c;
		return other.id - id;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Bookmark) {
			Bookmark other = (Bookmark) obj;
			return id == other.id;
		}
		return false;
	}
	
	public String toString() {
		return "Bookmark(" + String.valueOf(id) + ", User(" + userEmailAddress + "))";
	}
}

