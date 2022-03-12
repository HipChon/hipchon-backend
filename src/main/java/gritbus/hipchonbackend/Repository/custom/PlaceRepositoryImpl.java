package gritbus.hipchonbackend.Repository.custom;

import static gritbus.hipchonbackend.Domain.QCategory.*;
import static gritbus.hipchonbackend.Domain.QCity.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;

import java.util.List;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.QCategory;
import gritbus.hipchonbackend.Domain.QCity;
import gritbus.hipchonbackend.Domain.QPlace;

public class PlaceRepositoryImpl implements PlaceRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	public PlaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	@Override
	public List<Place> fastSearch(PlaceFastSearchCondition condition) {
		return queryFactory
			.select(place)
			.from(place)
			.join(place.city, city)
			.join(place.category, category)
			.where(cityEq(condition.getCityId()),
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

}
