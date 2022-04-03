package gritbus.hipchonbackend.Domain.Post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {
	@Id
	@GeneratedValue
	@Column(name = "post_image_id")
	private Long id;

	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	public PostImage(String image) {
		this.image = image;
	}

	public static PostImage createPostImage(Post post,String image){
		PostImage postImage = new PostImage(image);
		postImage.setPost(post);
		return postImage;
	}

	public void setPost(Post post) {
		this.post = post;
		post.getPostImageList().add(this);
	}
}
