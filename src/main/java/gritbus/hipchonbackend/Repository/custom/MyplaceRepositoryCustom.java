package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Cond.MyplaceCondition;
import gritbus.hipchonbackend.Domain.Myplace;
import gritbus.hipchonbackend.Dto.MyplaceDto;

public interface MyplaceRepositoryCustom {
	List<Myplace> findAllBy(MyplaceCondition myplaceCondition);

	List<MyplaceDto> findAllMyplace(Long userId);
}
