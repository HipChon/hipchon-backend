package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Cond.PlaceHashtagCondition;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Dto.PostDto;

public interface PostRepositoryCustom {
	List<PostDto> findAllOrByPlace(Long placeID);
	List<String> getImageList(Long postId);
	Boolean getIsMyplace(Long userId,Long placeId);
}
