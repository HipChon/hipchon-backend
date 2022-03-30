package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Domain.LocalHipster.LocalHipster;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterListDto;

public interface LocalHipsterRepositoryCustom {
	List<LocalHipsterListDto> findAllAsList();
}
