package gritbus.hipchonbackend.Repository.Impl;


import static gritbus.hipchonbackend.Domain.Post.QPost.*;
import static gritbus.hipchonbackend.Domain.Post.QPostKeywordReview.*;
import static gritbus.hipchonbackend.Domain.QKeywordReview.*;
import static gritbus.hipchonbackend.Domain.QMenu.*;

import java.util.List;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Domain.QMenu;
import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Dto.MenuDto;
import gritbus.hipchonbackend.Dto.QKeywordDto;
import gritbus.hipchonbackend.Dto.QMenuDto;
import gritbus.hipchonbackend.Repository.custom.KeywordReviewRepositoryCustom;
import gritbus.hipchonbackend.Repository.custom.MenuRepositoryCustom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MenuDto> findByPlaceId(Long placeId) {
		return queryFactory
			.select(new QMenuDto(
				menu.id,
				menu.name,
				menu.price,
				menu.image
			))
			.from(menu)
			.where(menu.place.id.eq(placeId))
			.limit(4)
			.fetch();
	}
}




