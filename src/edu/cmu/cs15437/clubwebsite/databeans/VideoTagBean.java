package edu.cmu.cs15437.clubwebsite.databeans;

public class VideoTagBean implements Comparable< VideoTagBean > {
	private int tagId				= -1;
	private int categoryId			= -1;
	private int videoId				= -1;
	
	public VideoTagBean(int tagId) {
		this.tagId = tagId;
	}
	
	public int getTagId()		{ return tagId;			}
	public int getCategoryId()	{ return categoryId;	}
	public int getVideoId()		{ return videoId;		}
	
	public void setCategoryId(int i)	{ categoryId = i;	}
	public void setVideoId(int i)		{ videoId = i;		}
	
	public int compareTo(VideoTagBean other) {
		// Order by recency
		return other.tagId - tagId;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof VideoTagBean) {
			VideoTagBean other = (VideoTagBean) obj;
			return tagId == other.tagId;
		}
		return false;
	}
	
	public String toString() {
		return "VideoTag( " + String.valueOf(tagId) + ", Category( " + String.valueOf(categoryId) + " ), Video( " + String.valueOf(videoId) + " ) )";
	}
}

