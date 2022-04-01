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

@Entity
@Getter
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

}
