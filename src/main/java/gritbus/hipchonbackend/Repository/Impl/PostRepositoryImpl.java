package gritbus.hipchonbackend.Repository.Impl;

import static com.querydsl.jpa.JPAExpressions.*;

import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;

import static gritbus.hipchonbackend.Domain.QPost.*;
import static gritbus.hipchonbackend.Domain.QPostComment.*;
import static gritbus.hipchonbackend.Domain.QPostImage.*;
import static gritbus.hipchonbackend.Domain.QUser.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Domain.QPost;

import gritbus.hipchonbackend.Domain.QUser;

import gritbus.hipchonbackend.Dto.*;

import gritbus.hipchonbackend.Repository.custom.PostRepositoryCustom;

public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public PostRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	//placeId가 -1이면 전체 post찾기
	@Override
	public List<PostDto> findAllOrByPlace(Long userId,Long placeID) {
		QUser subUser = new QUser("subUser");
		QPost subPost = new QPost("subPost");
		QPost subPost2 = new QPost("subPost2");

		List<PostDto> postDtoList = getPostDtoList(userId,placeID, subUser, subPost, subPost2);
		Map<Long, List<PostImageDto>> postImageMap = groupById(getImageList(toPostIdList(postDtoList)));
		postDtoList.forEach(p->p.setImageList(mapToImage(postImageMap, p.getId())));
		return postDtoList;
	}

	@Override
	public List<MypostDto> findByUser(Long userId) {
		List<MypostDto> mypostList = getMypostList(userId);
		Map<Long, List<PostImageDto>> postImageMap = groupById(getImageList(toMyPostIdList(mypostList)));
		mypostList.forEach(p-> p.setPostImage(getFirstImage(postImageMap,p.getId())));
		return mypostList;
	}

	private List<Long> toMyPostIdList(List<MypostDto> mypostList) {
		return mypostList.stream()
				.map(MypostDto::getId)
				.collect(Collectors.toList());
	}

	private List<MypostDto> getMypostList(Long userID) {
		return queryFactory
				.select(new QMypostDto(
						post.id,
						place.name
				))
				.from(post)
				.join(post.place, place)
				.where(post.user.id.eq(userID))
				.fetch();
	}

	private Map<Long, List<PostImageDto>> groupById(List<PostImageDto> ImageDto) {
		return ImageDto.stream()
			.collect(Collectors.groupingBy(p -> p.getPostId()));
	}

	private String getFirstImage(Map<Long, List<PostImageDto>> postImageMap, Long postId ) {
		if (hasImage(postImageMap, postId) && (postImageMap!=null) &&(postImageMap.get(postId)!=null)){
			return postImageMap.get(postId).get(0).getImage();
		}
		return null;
	}

	private List<String> mapToImage(Map<Long, List<PostImageDto>> postImageMap, Long postId ) {
		if (hasImage(postImageMap, postId) && (postImageMap!=null) && (postImageMap.get(postId)!=null)){
			return postImageMap.get(postId).stream()
				.map(PostImageDto::getImage)
				.collect(Collectors.toList());
		}
		return new ArrayList<>();

	}

	private boolean hasImage(Map<Long, List<PostImageDto>> postImageMap, Long postId) {
		return postImageMap.keySet().contains(postId);
	}

	private List<Long> toPostIdList(List<PostDto> postDtoList) {
		return postDtoList.stream()
			.map(PostDto::getId)
			.collect(Collectors.toList());
	}

	private List<PostDto> getPostDtoList(Long userId,Long placeID, QUser subUser, QPost subPost, QPost subPost2) {
		return queryFactory
			.select(new QPostDto(
				post.id,
				user.id,
				user.name,
				user.profileImage,
				post.postTime,
				getUserPostCnt(subUser, subPost),
				post.likeCnt,
				getCommentCnt(subPost2), //post의 comment 갯수
				post.detail,
				place.id,
				getIsMyplace(userId,place.id)
			))
			.from(post)
			.join(post.user, user)
			.join(post.place, place)
			.where(placeEq(placeID))
			.orderBy(post.id.desc())
			.fetch();
	}

	private JPQLQuery<Long> getUserPostCnt(QUser subUser, QPost subPost) {
		return select(subPost.id.count())
			.from(subPost)
			.join(subPost.user, subUser)
			.where(subUser.id.eq(user.id))
			.groupBy(subUser.id);
	}

	private JPQLQuery<Long> getCommentCnt(QPost subPost2) {
		return select(postComment.id.count())
			.from(postComment)
			.join(postComment.post, subPost2)
			.where(postComment.post.id.eq(post.id));
	}

	private List<PostImageDto> getImageList(List<Long> postIdList) {
		return queryFactory
			.select(new QPostImageDto(post.id,postImage.image))
			.from(postImage)
			.join(postImage.post,post)
			.where(post.id.in(postIdList))
			.fetch();
	}


	private BooleanExpression getIsMyplace(Long userId, NumberPath<Long> placeId) {
		return JPAExpressions
			.selectOne()
			.from(myplace)
			.where(
				myplace.user.id.eq(userId),
				myplace.place.id.eq(placeId))
			.exists();

	}

	private BooleanExpression placeEq(Long placeId) {
		if (placeId ==-1){
			return null;
		}
		return post.place.id.eq(placeId);
	}
}
