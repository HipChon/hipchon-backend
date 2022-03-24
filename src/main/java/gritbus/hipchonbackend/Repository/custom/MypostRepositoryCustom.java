package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Dto.MypostDto;

public interface MypostRepositoryCustom {
	List<MypostDto> findMypost(Long userId);

}
