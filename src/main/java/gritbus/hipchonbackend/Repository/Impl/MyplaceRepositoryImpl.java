package gritbus.hipchonbackend.Repository.Impl;

import static gritbus.hipchonbackend.Domain.QMyplace.*;
import static gritbus.hipchonbackend.Domain.QPlace.*;
import static gritbus.hipchonbackend.Domain.QUser.*;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Cond.MyplaceCondition;
import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Domain.QMyplace;
import gritbus.hipchonbackend.Domain.QPlace;
import gritbus.hipchonbackend.Domain.User;
import gritbus.hipchonbackend.Repository.custom.MyplaceRepositoryCustom;

public class MyplaceRepositoryImpl implements MyplaceRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public MyplaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<Myplace> findAllBy(MyplaceCondition condition){
		return queryFactory
			.select(myplace)
			.from(myplace)
			.where(
				userEq(condition.getUserId()),
				placeEq(condition.getPlaceId())
				)
			.fetch();
	}

	private BooleanExpression userEq(Long userId) {
		if (userId==null){
			return null;
		}
		return user.id.eq(userId);
	}

	private BooleanExpression placeEq(Long placeId) {
		if (placeId==null){
			return null;
		}
		return place.id.eq(placeId);
	}
}
