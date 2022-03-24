package gritbus.hipchonbackend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Dto.CityDto;
import gritbus.hipchonbackend.Repository.CityRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityService {
	private CityRepository cityRepository;

}
