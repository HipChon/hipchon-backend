package gritbus.hipchonbackend.Repository.Impl;



import static gritbus.hipchonbackend.Domain.Post.QMypost.*;
import static gritbus.hipchonbackend.Domain.Post.QPost.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Repository.Impl.PostRepositoryImpl.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Domain.Post.Mypost;
import gritbus.hipchonbackend.Domain.Post.QMypost;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.Post.PostImageDto;
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
		mypostList.forEach(p -> p.setImage(getFirstImage(postImageMap, p.getPostId())));
		return mypostList;
	}

	@Override
	public Optional<Mypost> findByUserIdAndPostId(Long userId, Long postId) {
		Mypost mypost = queryFactory
			.select(QMypost.mypost)
			.from(QMypost.mypost)
			.where(
				QMypost.mypost.user.id.eq(userId),
				QMypost.mypost.post.id.eq(postId)
			)
			.fetchFirst();

		return Optional.ofNullable(mypost);

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
			.orderBy(post.id.desc())
			.fetch();
	}
}
