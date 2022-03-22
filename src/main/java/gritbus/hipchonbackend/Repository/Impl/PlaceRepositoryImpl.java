package gritbus.hipchonbackend.Repository.Impl;

import static com.querydsl.jpa.JPAExpressions.*;
import static gritbus.hipchonbackend.Domain.QCategory.*;
import static gritbus.hipchonbackend.Domain.QCity.*;
import static gritbus.hipchonbackend.Domain.QHashtag.*;
import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QPlaceHashtag.*;
import static gritbus.hipchonbackend.Domain.QPost.*;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Cond.PlaceHashtagCondition;
import gritbus.hipchonbackend.Domain.QPlaceHashtag;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Dto.QHipleDto;
import gritbus.hipchonbackend.Dto.QPlaceListDto;
import gritbus.hipchonbackend.Repository.custom.PlaceRepositoryCustom;

public class PlaceRepositoryImpl implements PlaceRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public PlaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}


	@Override
	public List<PlaceListDto> findAllByHashtag(PlaceHashtagCondition condition) {
		return queryFactory
			.select(new QPlaceListDto(
				place.id,
				place.name,
				category.name,
				city.name,
				place.placeImage,
				getPostCnt(),
				getMyplaceCnt(),
				isMyplace(condition.getUserId())))
			.from(placeHashtag)
			.join(placeHashtag.place,place)
			.join(place.category,category)
			.join(place.city,city)
			.join(placeHashtag.hashtag,hashtag)
			.where(placeHashtag.hashtag.id.eq(condition.getHashtagId()))
			.fetch();
	}

	@Override
	public List<PlaceListDto> fastSearch(PlaceFastSearchCondition condition) {
		return queryFactory
			.select(new QPlaceListDto(
					place.id,
					place.name,
					category.name,
					city.name,
					place.placeImage,
					getPostCnt(),
					getMyplaceCnt(),
					isMyplace(condition.getUserId())
				)
			)
			.from(place)
			.join(place.category,category)
			.join(place.city,city)
			.where(
				cityEq(condition.getCityId()),
				categoryEq(condition.getCategoryId()))
			.fetch();
	}

	@Override
	public List<HipleDto> findAllByHiple(Long userId){
		return queryFactory
			.select(new QHipleDto(
					place.id,
					place.name,
					category.name,
					city.name,
					place.placeImage,
					getPostCnt(),
					getMyplaceCnt(),
					isMyplace(userId)
				)
			)
			.from(place)
			.join(place.category,category)
			.join(place.city,city)
			.where(
				place.hiple.eq(true))
			.fetch();
	}

	public static JPQLQuery<Long> getPostCnt(){
		return select(post.place.count())
			.from(post)
			.where(post.place.eq(place));
	}

	private JPQLQuery<Long> getMyplaceCnt(){
		return select(myplace.place.count())
			.from(myplace)
			.where(myplace.place.eq(place));
	}

	private BooleanExpression isMyplace(Long userId){
		return select(myplace)
			.from(myplace)
			.where(
				myplace.user.id.eq(userId),
				myplace.place.eq(place))
			.exists();
	}

	private BooleanExpression categoryEq(Long categoryId) {
		if (categoryId ==null){
			return null;
		}
		return category.id.eq(categoryId);
	}

	private BooleanExpression cityEq(Long cityId) {
		if (cityId==null){
			return null;
		}
		return city.id.eq(cityId);
	}

}
