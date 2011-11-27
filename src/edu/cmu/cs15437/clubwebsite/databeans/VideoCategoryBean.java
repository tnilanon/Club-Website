package edu.cmu.cs15437.clubwebsite.databeans;

public class VideoCategoryBean implements Comparable< VideoCategoryBean > {
	private int categoryId			= -1;
	private boolean linkedWithUser	= false;
	private String description		= null;
	
	public VideoCategoryBean(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public int getCategoryId()			{ return categoryId;		}
	public boolean isLinkedWithUser()	{ return linkedWithUser;	}
	public String getDescription()		{ return description;		}
	
	public void setLinkedWithUser(boolean b)	{ linkedWithUser = b;	}
	public void setDescription(String s)		{ description = s;		}
	
	public int compareTo(VideoCategoryBean other) {
		// Order by recency
		return other.categoryId - categoryId;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof VideoCategoryBean) {
			VideoCategoryBean other = (VideoCategoryBean) obj;
			return categoryId == other.categoryId;
		}
		return false;
	}
	
	public String toString() {
		return "VideoCategory( " + String.valueOf(categoryId) + ", Description( " + description + " ) )";
	}
}

