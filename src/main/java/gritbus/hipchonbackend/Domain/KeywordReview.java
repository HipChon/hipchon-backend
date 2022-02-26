package gritbus.hipchonbackend.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class KeywordReview {
	@Id @GeneratedValue
	@Column(name = "keyword_review_id")
	private Long id;

	@OneToMany(mappedBy = "keywordReview", cascade = CascadeType.ALL)
	private List<PostKeywordReview> postKeywordReviewList = new ArrayList<>();

	private String keyword;


}
