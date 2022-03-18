package gritbus.hipchonbackend.Repository.Impl;

import static gritbus.hipchonbackend.Domain.QKeywordReview.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QPost.*;
import static gritbus.hipchonbackend.Domain.QPostKeywordReview.*;

import java.util.List;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Dto.QKeywordDto;
import gritbus.hipchonbackend.Repository.custom.KeywordReviewRepositoryCustom;

public class KeywordReviewRepositoryImpl implements KeywordReviewRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public KeywordReviewRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<KeywordDto> getTop3(Long placeId) {
		NumberPath<Long> cnt = Expressions.numberPath(Long.class, "cnt");
		return queryFactory
			.select(new QKeywordDto(
				keywordReview.id,
				keywordReview.keyword,
				// keywordReview.category,
				postKeywordReview.count().as(cnt)
			))
			.from(postKeywordReview)
			.join(postKeywordReview.post, post)
			.join(postKeywordReview.keywordReview, keywordReview)
			.where(post.place.id.eq(placeId))
			.groupBy(postKeywordReview.keywordReview.id)
			.orderBy(cnt.desc(), keywordReview.id.desc())
			.limit(3)
			.fetch();
	}
}

		//여러 방법으로 해보는중
	// 	return queryFactory
	// 		.select(new QKeywordDto(
	// 			keywordReview.id,
	// 			keywordReview.keyword,
	// 			// keywordReview.category,
	// 			postKeywordReview.count().as(cnt)
	// 		))
	// 		.from(keywordReview)
	// 		.join(postKeywordReview)
	// 		.join(postKeywordReview.post,post)
	// 		.where(post.place.id.eq(placeId))
	// 		.groupBy(keywordReview.id)
	// 		.orderBy(cnt.desc(),keywordReview.id.desc())
	// 		.limit(3)
	// 		.fetch();
	// }


