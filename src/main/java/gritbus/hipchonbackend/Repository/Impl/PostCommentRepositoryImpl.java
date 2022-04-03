package gritbus.hipchonbackend.Repository.Impl;



import static gritbus.hipchonbackend.Domain.Post.QPost.*;
import static gritbus.hipchonbackend.Domain.Post.QPostComment.*;
import static gritbus.hipchonbackend.Domain.QUser.*;
import static gritbus.hipchonbackend.Repository.Impl.PostRepositoryImpl.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Dto.Post.CommentDto;
import gritbus.hipchonbackend.Dto.Post.MyCommentDto;
import gritbus.hipchonbackend.Dto.Post.PostImageDto;
import gritbus.hipchonbackend.Dto.Post.QCommentDto;
import gritbus.hipchonbackend.Dto.Post.QCommentUserDto;
import gritbus.hipchonbackend.Dto.Post.QMyCommentDto;
import gritbus.hipchonbackend.Dto.Post.QPostImageDto;
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

	@Override
	public List<MyCommentDto> findAllMyComment(Long userId) {
		List<MyCommentDto> commentList = queryFactory
			.select(new QMyCommentDto(
					postComment.id,
					postComment.commentTime,
					postComment.detail,
					new QPostImageDto(
						post.id
					)
				)
			)
			.from(postComment)
			.join(postComment.post, post)
			.where(postComment.user.id.eq(userId))
			.orderBy(postComment.commentTime.desc(), postComment.id.desc())
			.fetch();
		Map<Long, List<PostImageDto>> postImageMap = groupById(getImageList(queryFactory, toPostIdList(commentList)));
		commentList.forEach(c->c.getPost().setImage(getFirstImage(postImageMap,c.getPost().getPostId())));

		return commentList;
	}
	private List<Long> toPostIdList(List<MyCommentDto> commentList) {
		return commentList.stream()
			.map(c->c.getPost().getPostId())
			.collect(Collectors.toList());
	}

}


