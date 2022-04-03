package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Domain.Post.PostKeywordReview;
import gritbus.hipchonbackend.Dto.KeywordDto;

public interface KeywordReviewRepositoryCustom {
	List<KeywordDto> getTop(Long placeId,int n);

}
