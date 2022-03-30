package gritbus.hipchonbackend.Repository.Impl;

import static gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipster.*;
import static gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipsterPost.*;
import static gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipsterPostImage.*;
import static gritbus.hipchonbackend.Domain.QCity.*;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import gritbus.hipchonbackend.Domain.LocalHipster.LocalHipster;
import gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipster;
import gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipsterPost;
import gritbus.hipchonbackend.Domain.LocalHipster.QLocalHipsterPostImage;
import gritbus.hipchonbackend.Domain.QCity;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterListDto;
import gritbus.hipchonbackend.Dto.LocalHipster.QLocalHipsterListDto;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.Post.PostImageDto;
import gritbus.hipchonbackend.Dto.QMypostDto;
import gritbus.hipchonbackend.Repository.custom.LocalHipsterRepositoryCustom;
import gritbus.hipchonbackend.Repository.custom.MypostRepositoryCustom;
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
			.select(
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
}
