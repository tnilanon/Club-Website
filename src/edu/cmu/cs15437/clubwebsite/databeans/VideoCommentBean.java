package edu.cmu.cs15437.clubwebsite.databeans;

public class VideoCommentBean implements Comparable< VideoCommentBean > {
	private int commentId			= -1;
	private int videoId				= -1;
	private int ownerId				= -1;
	private String comment			= null;
	
	public VideoCommentBean(int commentId) {
		this.commentId = commentId;
	}
	
	public int getCommentId()	{ return commentId;	}
	public int getVideoId()		{ return videoId;	}
	public int getOwnerId()		{ return ownerId;	}
	public String getComment()	{ return comment;	}
	
	public void setVideoId(int i)		{ videoId = i;	}
	public void setOwnerId(int i)		{ ownerId = i;	}
	public void setComment(String s)	{ comment = s;	}
	
	public int compareTo(VideoCommentBean other) {
		// Order by recency
		return other.commentId - commentId;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof VideoCommentBean) {
			VideoCommentBean other = (VideoCommentBean) obj;
			return commentId == other.commentId;
		}
		return false;
	}
	
	public String toString() {
		return "VideoComment( " + String.valueOf(commentId) + ", Video( " + String.valueOf(videoId) + " ), Owner( " + String.valueOf(ownerId) + " ) )";
	}
}

