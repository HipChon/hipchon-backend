package gritbus.hipchonbackend.Repository.custom;

import static gritbus.hipchonbackend.Domain.QCategory.*;
import static gritbus.hipchonbackend.Domain.QCity.*;
import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QPost.*;
import static gritbus.hipchonbackend.Domain.QPostKeywordReview.*;
import static gritbus.hipchonbackend.Domain.QUser.*;

import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Domain.QPlace;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Dto.QPlaceListDto;

public class PlaceRepositoryImpl implements PlaceRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	public PlaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
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
					JPAExpressions
						.select(post.place.count())
						.from(post)
						.where(post.place.eq(place)),
					JPAExpressions
						.select(myplace.place.count())
						.from(myplace)
						.where(myplace.place.eq(place)),
					place.name.append(" 임시키워드입니다"),
					JPAExpressions
						.select(myplace)
						.from(myplace)
						.where(
							myplace.user.id.eq(condition.getUserId()),
							myplace.place.eq(place))
						.exists()
				)
			)
			.from(place)
			.join(place.category,category)
			.join(place.city,city)
			//일단 이거되느지 확인 하고 하기
			.where(
				cityEq(condition.getCityId()),
				categoryEq(condition.getCategoryId()))
			.fetch();
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
	private BooleanExpression userEq(Long userId) {
		if (userId==null){
			return null;
		}
		return user.id.eq(userId);
	}
}
