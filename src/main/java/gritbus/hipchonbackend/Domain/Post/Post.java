package gritbus.hipchonbackend.Domain.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Dto.Requset.PostRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id")
	private Place place;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostComment> commentList = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostKeywordReview> postKeywordReviewList = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<PostImage> postImageList = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Mypost> mypostList = new ArrayList<>();

	private String title;
	private Long viewCnt = 0L;
	private String detail;
	private LocalDateTime postTime;
	private Long rate = 0L;
	private Long likeCnt = 0L;

	private Boolean isBest = Boolean.FALSE;

	public Post(String detail) {
		this.detail = detail;
		this.postTime = LocalDateTime.now();
	}

	public static Post createPost(User user,Place place, String detail){
		Post post = new Post(detail);
		post.setUser(user);
		post.setPlace(place);
		return post;
	}

	public void setPlace(Place place) {
		this.place = place;
		place.getPostList().add(this);
	}

	public void setUser(User user) {
		this.user = user;
		user.getPostList().add(this);
	}

}
