package gritbus.hipchonbackend.Repository.Impl;

import static gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipster.*;
import static gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipsterPost.*;
import static gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipsterPostImage.*;
import static gritbus.hipchonbackend.Domain.QCategory.*;
import static gritbus.hipchonbackend.Domain.QCity.*;
import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;


import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterDto;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterListDto;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterPostDto;

import gritbus.hipchonbackend.Dto.LocalHipster.QLocalHipsterDto;
import gritbus.hipchonbackend.Dto.LocalHipster.QLocalHipsterListDto;
import gritbus.hipchonbackend.Dto.LocalHipster.QLocalHipsterPostDto;
import gritbus.hipchonbackend.Dto.Post.PostImageDto;
import gritbus.hipchonbackend.Dto.Post.QPostImageDto;
import gritbus.hipchonbackend.Dto.Post.QPostPlaceSummaryDto;
import gritbus.hipchonbackend.Repository.custom.LocalHipsterRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LocalHipsterRepositoryImpl implements LocalHipsterRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<LocalHipsterListDto> findAllAsList() {
		List<LocalHipsterListDto> hipsterList = queryFactory
			.select(new QLocalHipsterListDto(
				localHipster.id,
				localHipster.city.name,
				localHipster.title
			))
			.from(localHipster)
			.join(localHipster.city, city)
			.orderBy(localHipster.id.desc())
			.fetch();

		Map<Long, List<Tuple>> map = queryFactory
			.selectDistinct(
				localHipster.id,
				localHipsterPost.id,
				localHipsterPost.title,
				localHipsterPostImage.image
			)
			.from(localHipster)
			.join(localHipster.city, city)
			.leftJoin(localHipster.hipsterPostList, localHipsterPost)
			.leftJoin(localHipsterPost.hipsterPostImageList, localHipsterPostImage)
			.groupBy(localHipsterPost.id)
			.fetch()
			.stream()
			.collect(Collectors.groupingBy(h -> h.get(localHipster.id)));

		hipsterList.forEach(h->{
			if (map.containsKey(h.getId())){
				List<Tuple> tuples = map.get(h.getId());
				Tuple firstPost = tuples.get(0);
				h.setImage(firstPost.get(localHipsterPostImage.image));
				String summary = h.getCity()+" "+firstPost.get(localHipsterPost.title)+" 등 "+tuples.size()+"곳";
				h.setSummary(summary);
			}
		});

		return hipsterList;
	}

	@Override
	public LocalHipsterDto findById(Long userId, Long hipsterId) {
		//로컬 힙스터
		LocalHipsterDto localHipsterDto = queryFactory
			.select(new QLocalHipsterDto(
					localHipster.id,
					localHipster.title.prepend("로컬 힙스터 픽 - ")
				)
			)
			.from(localHipster)
			.where(localHipster.id.eq(hipsterId))
			.fetchFirst();

		//힙스터의 post들
		List<LocalHipsterPostDto> hipsterpostList = queryFactory
			.selectDistinct(
				new QLocalHipsterPostDto(
					localHipsterPost.id,
					localHipsterPost.title,
					localHipsterPost.detail,
					new QPostPlaceSummaryDto(
						place.id,
						place.name,
						place.category.name,
						place.address,
						JPAExpressions.select(myplace)
							.from(myplace)
							.where(
								myplace.user.id.eq(userId),
								myplace.place.eq(place))
							.exists(),
						place.homepage
					)
				)
			)
			.from(localHipster)
			.leftJoin(localHipster.hipsterPostList, localHipsterPost)
			.leftJoin(localHipsterPost.place, place)
			.join(place.category, category)
			.where(localHipster.id.eq(hipsterId))
			.orderBy(localHipsterPost.id.asc())
			.fetch();

		setDtoImageList(hipsterpostList);
		localHipsterDto.setPostList(hipsterpostList);

		return localHipsterDto;
	}

	private List<String> mapToImage(Map<Long, List<PostImageDto>> postImageMap, Long postId) {
		if (hasImage(postImageMap, postId) && (postImageMap != null) && (postImageMap.get(postId) != null)) {
			return postImageMap.get(postId).stream()
				.map(PostImageDto::getImage)
				.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	private List<Long> toPostIdList(List<LocalHipsterPostDto> postDtoList) {
		return postDtoList.stream()
			.map(LocalHipsterPostDto::getId)
			.collect(Collectors.toList());
	}

	private Map<Long, List<PostImageDto>> groupById(List<PostImageDto> imageList) {
		return imageList.stream()
			.collect(Collectors.groupingBy(p->p.getPostId()));
	}

	private boolean hasImage(Map<Long, List<PostImageDto>> placeImageMap, Long postId) {
		return placeImageMap.containsKey(postId);
	}

	private List<PostImageDto> getImageList(List<Long> postIdList) {
		List<PostImageDto> fetch = queryFactory.select(new QPostImageDto(
			localHipsterPostImage.localHipsterPost.id,
			localHipsterPostImage.image
		))
			.from(localHipsterPostImage)
			.where(localHipsterPostImage.localHipsterPost.id.in(postIdList))
			.fetch();

		for (PostImageDto postImageDto : fetch) {
			System.out.println(postImageDto.toString());
		}
		return fetch;
	}

	private void setDtoImageList(List<LocalHipsterPostDto> postDtoList){
		Map<Long, List<PostImageDto>> longListMap = groupById(getImageList(toPostIdList(postDtoList)));
		postDtoList.forEach(p->p.setImageList(mapToImage(longListMap,p.getId())));
	}
}
