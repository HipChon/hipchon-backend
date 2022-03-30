package gritbus.hipchonbackend.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterListDto;
import gritbus.hipchonbackend.Repository.CityRepository;
import gritbus.hipchonbackend.Repository.LocalHipsterRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocalHipsterService {
	private final LocalHipsterRepository localHipsterRepository;

	public List<LocalHipsterListDto> findAllAsList(){
		return localHipsterRepository.findAllAsList();
	}

}
