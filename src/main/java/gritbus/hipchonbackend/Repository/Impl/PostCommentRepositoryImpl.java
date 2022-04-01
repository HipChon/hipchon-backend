package gritbus.hipchonbackend.Repository.Impl;

import static gritbus.hipchonbackend.Domain.QKeywordReview.*;
import static gritbus.hipchonbackend.Domain.QPost.*;
import static gritbus.hipchonbackend.Domain.QPostComment.*;
import static gritbus.hipchonbackend.Domain.QPostKeywordReview.*;
import static gritbus.hipchonbackend.Domain.QUser.*;

import java.util.List;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Domain.QPostComment;
import gritbus.hipchonbackend.Domain.QUser;
import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Dto.Post.CommentDto;
import gritbus.hipchonbackend.Dto.Post.QCommentDto;
import gritbus.hipchonbackend.Dto.Post.QCommentUserDto;
import gritbus.hipchonbackend.Dto.QKeywordDto;
import gritbus.hipchonbackend.Repository.custom.KeywordReviewRepositoryCustom;
import gritbus.hipchonbackend.Repository.custom.PostCommentRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostCommentRepositoryImpl implements PostCommentRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<CommentDto> findAllByPostId(Long postId) {
		return queryFactory
			.select(new QCommentDto(
				postComment.id,
				postComment.commentTime,
				postComment.detail,
				new QCommentUserDto(
					user.id,
					user.name,
					user.profileImage
				)
			))
			.from(postComment)
			.join(postComment.user, user)
			.where(postComment.post.id.eq(postId))
			.orderBy(postComment.commentTime.desc(),postComment.id.desc())
			.fetch();
	}
}


