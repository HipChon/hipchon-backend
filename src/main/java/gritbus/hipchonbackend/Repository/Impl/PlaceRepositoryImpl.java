package gritbus.hipchonbackend.Repository.Impl;

import static com.querydsl.jpa.JPAExpressions.*;
import static gritbus.hipchonbackend.Domain.QCategory.*;
import static gritbus.hipchonbackend.Domain.QCity.*;
import static gritbus.hipchonbackend.Domain.QHashtag.*;
import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QPlaceHashtag.*;
import static gritbus.hipchonbackend.Domain.QPlaceImage.*;
import static gritbus.hipchonbackend.Domain.QPost.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Cond.PlaceHashtagCondition;
import gritbus.hipchonbackend.Domain.QPlaceImage;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceImageDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Dto.Post.PostImageDto;
import gritbus.hipchonbackend.Dto.QHipleDto;
import gritbus.hipchonbackend.Dto.QPlaceImageDto;
import gritbus.hipchonbackend.Dto.QPlaceListDto;
import gritbus.hipchonbackend.Repository.custom.PlaceRepositoryCustom;

public class PlaceRepositoryImpl implements PlaceRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public PlaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public static JPQLQuery<Long> getPostCnt() {
		return select(post.place.count())
			.from(post)
			.where(post.place.eq(place));
	}

	private String getFirstImage(Map<Long, List<PlaceImageDto>> placeImageDto, Long placeId) {
		if (hasImage(placeImageDto, placeId) && (placeImageDto != null) && (placeImageDto.get(placeId) != null)) {
			return placeImageDto.get(placeId).get(0).getImage();
		}
		return null;
	}

	private List<String> mapToImage(Map<Long, List<PlaceImageDto>> placeImageMap, Long placeId) {
		if (hasImage(placeImageMap, placeId) && (placeImageMap != null) && (placeImageMap.get(placeId) != null)) {
			return placeImageMap.get(placeId).stream()
				.map(PlaceImageDto::getImage)
				.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	private List<Long> toPlaceIdList(List<PlaceListDto> placeDtoList) {
		return placeDtoList.stream()
			.map(PlaceListDto::getPlaceId)
			.collect(Collectors.toList());
	}

	private Map<Long, List<PlaceImageDto>> groupById(List<PlaceImageDto> imageList) {
		return imageList.stream()
			.collect(Collectors.groupingBy(p -> p.getPlaceId()));
	}

	private boolean hasImage(Map<Long, List<PlaceImageDto>> placeImageMap, Long placeId) {
		return placeImageMap.keySet().contains(placeId);
	}

	private List<PlaceImageDto> getImageList(List<Long> placeIdList) {
		return queryFactory.select(new QPlaceImageDto(
			placeImage.place.id,
			placeImage.image
		))
		.from(placeImage)
		.where(placeImage.place.id.in(placeIdList))
		.fetch();
	}

	private void setDtoImageList(List<PlaceListDto> placeListDtos){
		Map<Long, List<PlaceImageDto>> longListMap = groupById(getImageList(toPlaceIdList(placeListDtos)));
		placeListDtos.forEach(p->p.setImageList(mapToImage(longListMap,p.getPlaceId())));
	}

	@Override
	public List<PlaceListDto> findAllByHashtag(PlaceHashtagCondition condition) {
		List<PlaceListDto> placeListDtos = queryFactory
			.select(new QPlaceListDto(
					place.id,
					place.name,
					category.name,
					city.name,
					getPostCnt(),
					getMyplaceCnt(),
					isMyplace(condition.getUserId())
				)
			)
			.from(placeHashtag)
			.join(placeHashtag.place, place)
			.join(place.category, category)
			.join(place.city, city)
			.join(placeHashtag.hashtag, hashtag)
			.where(placeHashtag.hashtag.id.eq(condition.getHashtagId()))
			.orderBy(place.id.desc())
			.fetch();

		setDtoImageList(placeListDtos);
		return placeListDtos;
	}


	@Override
	public List<PlaceListDto> fastSearch(PlaceFastSearchCondition condition) {
		List<PlaceListDto> placeListDtos = queryFactory
			.select(new QPlaceListDto(
					place.id,
					place.name,
					category.name,
					city.name,
					getPostCnt(),
					getMyplaceCnt(),
					isMyplace(condition.getUserId())
				)
			)
			.from(place)
			.join(place.category, category)
			.join(place.city, city)
			.where(
				cityEq(condition.getCityId()),
				categoryEq(condition.getCategoryId()))
			.orderBy(place.id.desc())
			.fetch();
		setDtoImageList(placeListDtos);
		return placeListDtos;
	}

	@Override
	public List<HipleDto> findAllByHiple(Long userId) {
		List<HipleDto> hipleDtos = queryFactory
			.select(new QHipleDto(
					place.id,
					place.name,
					category.name,
					city.name,
					getPostCnt(),
					getMyplaceCnt(),
					isMyplace(userId)
				)
			)
			.from(place)
			.join(place.category, category)
			.join(place.city, city)
			.where(
				place.hiple.eq(true))
			.orderBy(place.id.desc())
			.fetch();
		Map<Long, List<PlaceImageDto>> longListMap = groupById(
			getImageList(
				hipleDtos.stream()
				.map(HipleDto::getPlaceId)
				.collect(Collectors.toList())));
		hipleDtos.forEach(p->p.setPlaceImage(getFirstImage(longListMap,p.getPlaceId())));
		return hipleDtos;
	}

	private JPQLQuery<Long> getMyplaceCnt() {
		return select(myplace.place.count())
			.from(myplace)
			.where(myplace.place.eq(place));
	}

	private BooleanExpression isMyplace(Long userId) {
		return select(myplace)
			.from(myplace)
			.where(
				myplace.user.id.eq(userId),
				myplace.place.eq(place))
			.exists();
	}

	private BooleanExpression categoryEq(Long categoryId) {
		if (categoryId == -1) {
			return null;
		}
		return category.id.eq(categoryId);
	}

	private BooleanExpression cityEq(Long cityId) {
		if (cityId == -1) {
			return null;
		}
		return city.id.eq(cityId);
	}

}
