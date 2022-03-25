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
import java.util.stream.Collectors;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Domain.QPost;
import gritbus.hipchonbackend.Domain.QUser;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.PostDto;
import gritbus.hipchonbackend.Dto.PostImageDto;
import gritbus.hipchonbackend.Dto.QMypostDto;
import gritbus.hipchonbackend.Dto.QPostDto;
import gritbus.hipchonbackend.Dto.QPostImageDto;
import gritbus.hipchonbackend.Repository.custom.PostRepositoryCustom;

public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public PostRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public static List<Long> toMyPostIdList(List<MypostDto> mypostList) {
		return mypostList.stream()
			.map(MypostDto::getPostId)
			.collect(Collectors.toList());
	}

	public static Map<Long, List<PostImageDto>> groupById(List<PostImageDto> ImageDto) {
		return ImageDto.stream()
			.collect(Collectors.groupingBy(p -> p.getPostId()));
	}

	public static String getFirstImage(Map<Long, List<PostImageDto>> postImageMap, Long postId) {
		if (hasImage(postImageMap, postId) && (postImageMap != null) && (postImageMap.get(postId) != null)) {
			return postImageMap.get(postId).get(0).getImage();
		}
		return null;
	}

	public static boolean hasImage(Map<Long, List<PostImageDto>> postImageMap, Long postId) {
		return postImageMap.keySet().contains(postId);
	}

	public static List<PostImageDto> getImageList(JPAQueryFactory queryFactory, List<Long> postIdList) {
		return queryFactory
			.select(new QPostImageDto(post.id, postImage.image))
			.from(postImage)
			.join(postImage.post, post)
			.where(post.id.in(postIdList))
			.fetch();
	}

	//placeId가 -1이면 전체 post찾기
	@Override
	public List<PostDto> findAllOrByPlace(Long userId, Long placeID) {
		QUser subUser = new QUser("subUser");
		QPost subPost = new QPost("subPost");
		QPost subPost2 = new QPost("subPost2");

		List<PostDto> postDtoList = getPostDtoList(userId, placeID, subUser, subPost, subPost2);
		Map<Long, List<PostImageDto>> postImageMap = groupById(getImageList(queryFactory, toPostIdList(postDtoList)));
		postDtoList.forEach(p -> p.setImageList(mapToImage(postImageMap, p.getPostId())));
		return postDtoList;
	}

	@Override
	public List<MypostDto> findByUser(Long userId) {
		List<MypostDto> mypostList = getMypostList(userId);
		Map<Long, List<PostImageDto>> postImageMap = groupById(getImageList(queryFactory, toMyPostIdList(mypostList)));
		mypostList.forEach(p -> p.setPostImage(getFirstImage(postImageMap, p.getPostId())));
		return mypostList;
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

	private List<String> mapToImage(Map<Long, List<PostImageDto>> postImageMap, Long postId) {
		if (hasImage(postImageMap, postId) && (postImageMap != null) && (postImageMap.get(postId) != null)) {
			return postImageMap.get(postId).stream()
				.map(PostImageDto::getImage)
				.collect(Collectors.toList());
		}
		return new ArrayList<>();

	}

	private List<Long> toPostIdList(List<PostDto> postDtoList) {
		return postDtoList.stream()
			.map(PostDto::getPostId)
			.collect(Collectors.toList());
	}

	private List<PostDto> getPostDtoList(Long userId, Long placeID, QUser subUser, QPost subPost, QPost subPost2) {
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
				getIsMyplace(userId, place.id)
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
		if (placeId == -1) {
			return null;
		}
		return post.place.id.eq(placeId);
	}
}
