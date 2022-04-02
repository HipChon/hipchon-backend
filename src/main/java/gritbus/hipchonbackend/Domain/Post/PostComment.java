package gritbus.hipchonbackend.Domain.Post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gritbus.hipchonbackend.Domain.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PostComment {
	@Id
	@GeneratedValue
	@Column(name = "post_comment_id")
	private Long id;

	private String detail;
	private LocalDateTime commentTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	private Boolean isRemoved = false;

	public static PostComment createPostComment(User user, Post post,String detail) {
		PostComment postComment = new PostComment();
		postComment.setPost(post);
		postComment.setUser(user);
		postComment.setCommentTime(LocalDateTime.now());
		postComment.setDetail(detail);
		return postComment;
	}

	private void setPost(Post post) {
		this.post = post;
		post.getCommentList().add(this);
	}

	private void setUser(User user) {
		this.user = user;
		user.getPostCommentList().add(this);
	}
}
