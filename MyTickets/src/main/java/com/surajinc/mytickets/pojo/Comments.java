package com.surajinc.mytickets.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Comments")
public class Comments {

	public Comments(){
		
	}
	
	@Id
	@Column(name="COMMENT_ID", unique = true, nullable = false)
	private Integer commentId;
	
	@Column
	private String comment;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Movie movie;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
}
