package gritbus.hipchonbackend.Domain.Post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gritbus.hipchonbackend.Domain.KeywordReview;
import lombok.Getter;

@Entity
@Getter
public class PostKeywordReview {
	@Id
	@GeneratedValue
	@Column(name = "post_keyword_review_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "keyword_review_id")
	private KeywordReview keywordReview;

	public static PostKeywordReview createPostKeywordReview(Post post, KeywordReview keywordReview) {
		PostKeywordReview postKeywordReview = new PostKeywordReview();
		postKeywordReview.setKeywordReview(keywordReview);
		postKeywordReview.setPost(post);
		return postKeywordReview;
	}

	private void setPost(Post post) {
		this.post = post;
		post.getPostKeywordReviewList().add(this);
	}

	private void setKeywordReview(KeywordReview keywordReview) {
		this.keywordReview = keywordReview;
		keywordReview.getPostKeywordReviewList().add(this);
	}

}
