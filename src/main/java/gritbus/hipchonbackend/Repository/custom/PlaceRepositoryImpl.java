package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Dto.PlaceDto;

public class PlaceRepositoryImpl implements PlaceRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	public PlaceRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}


}
