package gritbus.hipchonbackend.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class Mypost {
	@Id
	@GeneratedValue
	@Column(name = "mypost_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post")
	private Post post;

	private void setPost(Post post){
		this.post= post;
		post.getMypostList().add(this);
	}

	private void setUser(User user){
		this.user = user;
		user.getMypostList().add(this);
	}

	public static Mypost createMypost(User user, Post post){
		Mypost mypost = new Mypost();
		mypost.setPost(post);
		mypost.setUser(user);
		return mypost;
	}
}
