package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Cond.PlaceHashtagCondition;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Dto.PostDto;

public interface PostRepositoryCustom {
	List<PostDto> findAllOrByPlace(Long userId,Long placeID);
	List<MypostDto> findByUser(Long userId);

	// Boolean getIsMyplace(Long userId,Long placeId);
}
