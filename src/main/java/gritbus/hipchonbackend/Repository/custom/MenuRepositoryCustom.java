package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Dto.MenuDto;

public interface MenuRepositoryCustom {

	List<MenuDto> findByPlaceId(Long placeId);

}
