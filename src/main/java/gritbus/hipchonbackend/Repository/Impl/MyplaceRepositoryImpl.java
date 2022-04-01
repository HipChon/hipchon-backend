package gritbus.hipchonbackend.Repository.Impl;

import static com.querydsl.jpa.JPAExpressions.*;

import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QPlaceImage.*;
import static gritbus.hipchonbackend.Repository.Impl.PlaceRepositoryImpl.*;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Cond.MyplaceCondition;
import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.QMyplace;
import gritbus.hipchonbackend.Domain.QPlace;
import gritbus.hipchonbackend.Dto.MyplaceDto;
import gritbus.hipchonbackend.Dto.QMyplaceDto;
import gritbus.hipchonbackend.Repository.custom.MyplaceRepositoryCustom;

public class MyplaceRepositoryImpl implements MyplaceRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public MyplaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public void delete(Long userId, Long placeId) {
		queryFactory.delete(myplace)
			.where(
				myplace.user.id.eq(userId),
				myplace.place.id.eq(placeId)
			)
			.execute();
	}

	@Override
	public Optional<Myplace> findByUserIdAndPlaceId(Long userId, Long placeId) {
		Myplace myplace = queryFactory
			.select(QMyplace.myplace)
			.from(QMyplace.myplace)
			.where(
				QMyplace.myplace.user.id.eq(userId),
				QMyplace.myplace.place.id.eq(placeId)
			)
			.fetchFirst();

		return Optional.ofNullable(myplace);
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
			.orderBy(myplace.id.desc())
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
				JPAExpressions
					.select(placeImage.image)
					.from(placeImage)
					.where(placeImage.place.eq(place)),
				getMyplaceCnt(subMypalce),
				getPostCnt()
				//comm
			))
			.from(myplace)
			.join(myplace.place, place)
			.where(myplace.user.id.eq(userId))
			.orderBy(myplace.id.desc())
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
		return myplace.user.id.eq(userId);
	}

	private BooleanExpression placeEq(Long placeId) {
		if (placeId == null) {
			return null;
		}
		return myplace.place.id.eq(placeId);
	}
}
