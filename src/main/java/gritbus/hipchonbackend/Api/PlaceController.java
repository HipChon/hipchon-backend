package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.PlaceDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Service.PlaceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
@ApiOperation(value = "장소 API")
public class PlaceController {

	private final PlaceService placeService;

	@Operation(summary = "장소 상세페이지 보여주는 API", description = "myplace인지 확인하기 위해 userId를 받습니다")
	@GetMapping("/{user_id}/{place_id}")
	public ResponseEntity<PlaceDto> findById(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(required = true, example = "1") @PathVariable("place_id") Long placeId) {
		return ResponseEntity.ok(placeService.findById(userId, placeId));
	}

	@Operation(summary = "빠른검색 API", description = "order는 myplace일 때 저장순,\npost일때는 후기순입니다")
	@GetMapping("/{user_id}/{city_id}/{category_id}/{order}")
	public ResponseEntity<List<PlaceListDto>> fastSearch(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(required = true, example = "2") @PathVariable("city_id") Long cityId,
		@Parameter(required = true, example = "1") @PathVariable("category_id") Long categoryId,
		@Parameter(required = true, example = "myplace") @PathVariable("order") String order) {
		return ResponseEntity.ok(placeService.fastSearch(userId, cityId, categoryId, order));
	}

	@Operation(summary = "hashtag로 장소 검색 API", description = "order는 myplace일 때 저장순,\npost일때는 후기순입니다")
	@GetMapping("/hashtag/{user_id}/{hashtag_id}/{order}")
	public ResponseEntity<List<PlaceListDto>> findAllByHashtag(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(required = true, example = "1") @PathVariable("hashtag_id") Long hashtagId,
		@Parameter(required = true, example = "myplace") @PathVariable("order") String order) {
		return ResponseEntity.ok(placeService.findAllByHashtag(userId, hashtagId, order));
	}

	@Operation(summary = "힙플 검색 API", description = "")
	@GetMapping("/hiple/{user_id}")
	public ResponseEntity<List<HipleDto>> findAllByHiple(@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId) {
		return ResponseEntity.ok(placeService.findAllByHiple(userId));
	}


}
