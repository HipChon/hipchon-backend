package gritbus.hipchonbackend.Repository.Impl;

import static com.querydsl.jpa.JPAExpressions.*;

import static gritbus.hipchonbackend.Domain.QCategory.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;

import static gritbus.hipchonbackend.Domain.QPost.*;
import static gritbus.hipchonbackend.Domain.QPostComment.*;
import static gritbus.hipchonbackend.Domain.QPostImage.*;
import static gritbus.hipchonbackend.Domain.QUser.*;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gritbus.hipchonbackend.Domain.QPost;

import gritbus.hipchonbackend.Domain.QPostImage;
import gritbus.hipchonbackend.Domain.QUser;

import gritbus.hipchonbackend.Dto.PostDto;

import gritbus.hipchonbackend.Dto.QPostDto;

import gritbus.hipchonbackend.Repository.custom.PostRepositoryCustom;

public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public PostRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	//placeId가 -1이면 전체 post찾기
	@Override
	public List<PostDto> findAllOrByPlace(Long placeID) {
		QUser subUser = new QUser("subUser");
		QPost subPost = new QPost("subPost");
		QPost subPost2 = new QPost("subPost2");
		return queryFactory
			.select(new QPostDto(
				post.id,
				user.id,
				user.name,
				user.profileImage,
				post.postTime,
				select(subPost.id.count())
					.from(subPost)
					.join(subPost.user ,subUser)
					.where(subUser.id.eq(user.id))
					.groupBy(subUser.id)
				,
				post.likeCnt,
				select(postComment.id.count())
					.from(postComment)
					.join(postComment.post,subPost2)
					.where(postComment.post.id.eq(post.id))
				, //post의 comment 갯수
				post.detail
			))
			.from(post)
			.join(post.user,user)
			.join(post.place,place)
			.where(placeEq(placeID))
			.orderBy(post.id.desc())
			.fetch();
	}

	@Override
	public List<String> getImageList(Long postId) {
		return queryFactory
			.select(postImage.image)
			.from(postImage)
			.join(postImage.post,post)
			.where(post.id.eq(postId))
			.fetch();
	}

	private BooleanExpression placeEq(Long placeId) {
		if (placeId ==-1){
			return null;
		}
		return post.place.id.eq(placeId);
	}
}
