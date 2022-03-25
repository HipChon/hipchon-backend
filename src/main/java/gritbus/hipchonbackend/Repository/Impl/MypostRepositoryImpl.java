package gritbus.hipchonbackend.Repository.Impl;

import static gritbus.hipchonbackend.Domain.QMypost.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QPost.*;
import static gritbus.hipchonbackend.Domain.QUser.*;
import static gritbus.hipchonbackend.Repository.Impl.PostRepositoryImpl.*;

import java.util.List;
import java.util.Map;

import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.PostImageDto;
import gritbus.hipchonbackend.Dto.QMypostDto;
import gritbus.hipchonbackend.Repository.custom.MypostRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MypostRepositoryImpl implements MypostRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<MypostDto> findMypost(Long userId) {
		List<MypostDto> mypostList = getMypostDtoList(userId);
		Map<Long, List<PostImageDto>> postImageMap = groupById(getImageList(queryFactory, toMyPostIdList(mypostList)));
		mypostList.forEach(p -> p.setPostImage(getFirstImage(postImageMap, p.getPostId())));
		return mypostList;
	}

	private List<MypostDto> getMypostDtoList(Long userId) {
		return queryFactory
			.select(new QMypostDto(
				post.id,
				post.place.name
			))
			.from(mypost)
			.join(mypost.post, post)
			.join(post.place, place)
			.where(
				mypost.user.id.eq(userId),
				post.id.eq(mypost.post.id),
				place.id.eq(post.place.id)
			)
			.fetch();
	}
}
