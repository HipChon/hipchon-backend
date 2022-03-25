package gritbus.hipchonbackend.Repository.Impl;

import static com.querydsl.jpa.JPAExpressions.*;
import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QUser.*;
import static gritbus.hipchonbackend.Repository.Impl.PlaceRepositoryImpl.*;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Cond.MyplaceCondition;
import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.QMyplace;
import gritbus.hipchonbackend.Dto.MyplaceDto;
import gritbus.hipchonbackend.Dto.QMyplaceDto;
import gritbus.hipchonbackend.Repository.custom.MyplaceRepositoryCustom;

public class MyplaceRepositoryImpl implements MyplaceRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public MyplaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<Myplace> findAllBy(MyplaceCondition condition) {
		return queryFactory
			.select(myplace)
			.from(myplace)
			.where(
				userEq(condition.getUserId()),
				placeEq(condition.getPlaceId())
			)
			.fetch();
	}

	@Override
	public List<MyplaceDto> findAllMyplace(Long userId) {
		QMyplace subMypalce = new QMyplace("subMyplace");
		return queryFactory
			.select(new QMyplaceDto(
				place.id,
				place.name,
				place.category.name,
				place.address,
				place.placeImage,
				getMyplaceCnt(subMypalce),
				getPostCnt()
				//comm
			))
			.from(myplace)
			.join(myplace.place, place)
			.where(myplace.user.id.eq(userId))
			.fetch();
	}

	private JPQLQuery<Long> getMyplaceCnt(QMyplace subMypalce) {
		return select(subMypalce.place.count())
			.from(subMypalce)
			.where(subMypalce.place.eq(place));
	}

	private BooleanExpression userEq(Long userId) {
		if (userId == null) {
			return null;
		}
		return user.id.eq(userId);
	}

	private BooleanExpression placeEq(Long placeId) {
		if (placeId == null) {
			return null;
		}
		return place.id.eq(placeId);
	}
}
