package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import com.querydsl.core.Tuple;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;

public interface PlaceRepositoryCustom {
	List<PlaceListDto> fastSearch(PlaceFastSearchCondition condition);
	List<HipleDto> findAllByHiple(Long userId);

}
