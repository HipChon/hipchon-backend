package gritbus.hipchonbackend.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Domain.Category;
import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.PlaceHashtag;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Repository.CategoryRepository;
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
	private final CategoryRepository categoryRepository;
	private final PlaceHashtagRepository placeHashtagRepository;

	public List<HipleDto> findAllByHiple(String hiple,Long userId){
		Boolean is = Boolean.valueOf(hiple);
		List<Place> allByHiple = placeRepository.findAllByHiple(is);
		return allByHiple.stream()
			.map(place -> HipleDto.of(place,userId))
			.collect(Collectors.toList());
	}

	public List<PlaceDto> findByHashtag(Long hashtagId,Long userId){
		Optional<Hashtag> hashtag = hashtagRepository.findById(hashtagId);
		List<PlaceHashtag> allByHashtag = placeHashtagRepository.findAllByHashtag(hashtag.get());
		return allByHashtag.stream()
			.map(PlaceHashtag::getPlace)
			.map(place -> PlaceDto.of(place,userId))
			.collect(Collectors.toList());
	}

	public List<PlaceListDto> fastSearch(Long userId,Long cityId, Long categoryId){
		System.out.println("service");
		System.out.println("cityId = " + cityId);
		System.out.println("categoryId = " + categoryId);
		List<PlaceListDto> placeList = placeRepository.fastSearch(new PlaceFastSearchCondition(userId,cityId,categoryId));
		// Optional<City> city = cityRepository.findById(cityId);
		// Optional<Category> category = categoryRepository.findById(categoryId);
		// List<Place> byCategoryAndCity = placeRepository.findByCategoryAndCity(category.get(), city.get());

		// return placeList.stream()
		// 	.sorted(Comparator.comparing(Place::getPostCount).reversed()) // 후기순으로 정렬 여기서도 쿼리 날린다
		// 	.map(place -> PlaceListDto.of(place,userId))
		// 	.collect(Collectors.toList());
		return placeList;
	}

	// private List<Place> filterByAnimal(List<Place> filtered, Boolean animal){
	// 	filtered = filtered.stream()
	// 		.filter(place -> place.getAnimal().equals(animal))
	// 		.collect(Collectors.toList());
	// 	return filtered;
	// }
	//
	// private List<Place> filterByCityId(List<Place> filtered, Long cityId){
	// 	if (cityId!=-1){
	// 		filtered = filtered.stream()
	// 			.filter(place -> place.getCity().getId().equals(cityId))
	// 			.collect(Collectors.toList());
	// 	}
	// 	return filtered;
	// }
	//
	// private List<Place> filterByHashtagId(List<Place> filtered, Long hashtagId){
	// 	if (hashtagId!=-1){
	// 		filtered = filtered.stream()
	// 			.filter(place -> place.containHashtag(hashtagId))
	// 			.collect(Collectors.toList());
	// 	}
	// 	return filtered;
	// }


}
