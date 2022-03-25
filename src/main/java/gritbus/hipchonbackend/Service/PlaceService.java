package gritbus.hipchonbackend.Service;

import static java.util.Comparator.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gritbus.hipchonbackend.Cond.PlaceFastSearchCondition;
import gritbus.hipchonbackend.Cond.PlaceHashtagCondition;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.KeywordDto;
import gritbus.hipchonbackend.Dto.PlaceDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Repository.KeywordReviewRepository;
import gritbus.hipchonbackend.Repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceService {
	private final PlaceRepository placeRepository;
	private final KeywordReviewRepository keywordReviewRepository;

	public PlaceDto findById(Long placeId, Long userId) {
		Optional<Place> byId = placeRepository.findById(placeId);
		return PlaceDto.of(byId.get(), userId);
	}

	public List<HipleDto> findAllByHiple(Long userId) {
		return placeRepository.findAllByHiple(userId);
	}

	public List<PlaceListDto> findAllByHashtag(Long hashtagId, Long userId, String order) {
		List<PlaceListDto> placeList = placeRepository.findAllByHashtag(new PlaceHashtagCondition(hashtagId, userId));
		addTopKeyword(placeList);
		return orderPlaceList(placeList, order);
	}

	public List<PlaceListDto> fastSearch(Long userId, Long cityId, Long categoryId, String order) {
		List<PlaceListDto> placeList = placeRepository.fastSearch(
			new PlaceFastSearchCondition(userId, cityId, categoryId));
		//성능 개선 꼭 하기!!
		addTopKeyword(placeList);
		return orderPlaceList(placeList, order);

		//느린버전
		// Optional<City> city = cityRepository.findById(cityId);
		// Optional<Category> category = categoryRepository.findById(categoryId);
		// List<Place> byCategoryAndCity = placeRepository.findByCategoryAndCity(category.get(), city.get());

		// return placeList.stream()
		// 	.sorted(Comparator.comparing(Place::getPostCount).reversed()) // 후기순으로 정렬 여기서도 쿼리 날린다
		// 	.map(place -> PlaceListDto.of(place,userId))
		// 	.collect(Collectors.toList());
	}

	private void addTopKeyword(List<PlaceListDto> placeList) {
		for (PlaceListDto p : placeList) {
			List<KeywordDto> top1 = keywordReviewRepository.getTop1(p.getPlaceId());
			if (top1.size() > 0) {
				p.setKeyword(top1.get(0).getKeyword());
				p.setKeywordCategory(top1.get(0).getCategory());
				p.setKeywordEmoji(top1.get(0).getEmoji());
			}
		}
	}

	private List<PlaceListDto> orderPlaceList(List<PlaceListDto> placeList, String order) {
		return placeList.stream()
			.sorted(comparing(orderBy(order)).reversed())
			.collect(Collectors.toList());
	}

	private Function<PlaceListDto, Long> orderBy(String order) {
		if (order.equals("myplace")) {
			return PlaceListDto::getMyplaceCnt;
		}
		return PlaceListDto::getPostCnt;
	}

	//위에꺼랑 성능 비교하기 -- 느린버전
	// public List<PlaceDto> findByHashtag(Long hashtagId,Long userId){
	// 	Optional<Hashtag> hashtag = hashtagRepository.findById(hashtagId);
	// 	List<PlaceHashtag> allByHashtag = placeHashtagRepository.findAllByHashtag(hashtag.get());
	// 	return allByHashtag.stream()
	// 		.map(PlaceHashtag::getPlace)
	// 		.map(place -> PlaceDto.of(place,userId))
	// 		.collect(Collectors.toList());
	// }

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
