package gritbus.hipchonbackend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.PlaceHashtag;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceDto;
import gritbus.hipchonbackend.Repository.CityRepository;
import gritbus.hipchonbackend.Repository.HashtagRepository;
import gritbus.hipchonbackend.Repository.PlaceHashtagRepository;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {
	private final PlaceRepository placeRepository;
	private final CityRepository cityRepository;
	private final HashtagRepository hashtagRepository;
	private final PlaceHashtagRepository placeHashtagRepository;

	public List<HipleDto> findAllByHiple(String hiple,Long userId){
		Boolean is = Boolean.valueOf(hiple);
		List<Place> allByHiple = placeRepository.findAllByHiple(is);
		return allByHiple.stream()
			.map(place -> HipleDto.of(place,userId))
			.collect(Collectors.toList());
	}

	public List<PlaceDto> findByHashtag(Long hashtagId){
		Optional<Hashtag> hashtag = hashtagRepository.findById(hashtagId);
		List<PlaceHashtag> allByHashtag = placeHashtagRepository.findAllByHashtag(hashtag.get());
		return allByHashtag.stream()
			.map(PlaceHashtag::getPlace)
			.map(place -> PlaceDto.of(place))
			.collect(Collectors.toList());
	}

	public List<PlaceDto> fastSearch(Long people, Boolean animal, Long cityId, Long hashtagId){
		List<Place> filtered = placeRepository.findByPeopleGreaterThanEqual(people);
		filtered = filterByAnimal(filtered,animal);
		filtered = filterByCityId(filtered, cityId);
		filtered = filterByHashtagId(filtered, hashtagId);

		return filtered.stream()
			.map(place -> PlaceDto.of(place))
			.collect(Collectors.toList());
	}

	private List<Place> filterByAnimal(List<Place> filtered, Boolean animal){
		filtered = filtered.stream()
			.filter(place -> place.getAnimal().equals(animal))
			.collect(Collectors.toList());
		return filtered;
	}

	private List<Place> filterByCityId(List<Place> filtered, Long cityId){
		if (cityId!=-1){
			filtered = filtered.stream()
				.filter(place -> place.getCity().getId().equals(cityId))
				.collect(Collectors.toList());
		}
		return filtered;
	}

	private List<Place> filterByHashtagId(List<Place> filtered, Long hashtagId){
		if (hashtagId!=-1){
			filtered = filtered.stream()
				.filter(place -> place.containHashtag(hashtagId))
				.collect(Collectors.toList());
		}
		return filtered;
	}


}
