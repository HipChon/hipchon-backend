package gritbus.hipchonbackend.Repository.custom;

import java.util.List;

import gritbus.hipchonbackend.Domain.LocalHipster.LocalHipster;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterDto;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterListDto;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterPostDto;

public interface LocalHipsterRepositoryCustom {
	List<LocalHipsterListDto> findAllAsList();
	LocalHipsterDto findById(Long userId,Long id);
}
