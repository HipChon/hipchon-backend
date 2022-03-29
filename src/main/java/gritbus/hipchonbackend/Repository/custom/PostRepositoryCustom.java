package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.PostDto;

public interface PostRepositoryCustom {
	List<PostDto> findAllOrByPlace(Long userId, Long placeID);
	List<MypostDto> findByUser(Long userId);

}
