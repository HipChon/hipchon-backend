package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlaceApiController {

	private final PlaceService placeService;

	@GetMapping("/api/place/{user_id}/{city_id}/{hashtag_id}")
	public Result fastSearch(@PathVariable("user_id") Long userId,
			@PathVariable("people") Long people,
			@PathVariable("animal") Boolean animal,
			@PathVariable("city_id") Long cityId,
			@PathVariable("hashtag_id") Long hashtagId){
		return new Result(placeService.fastSearch(userId,cityId,hashtagId));
	}

	@GetMapping("/api/place/{user_id}/{hashtag_id}")
	public Result findByHashtag(@PathVariable("hashtag_id") Long hashtagId,@PathVariable("user_id") Long userId){
		return new Result(placeService.findByHashtag(hashtagId,userId));
	}

	@GetMapping("/api/place/{user_id}/{hiple}")
	public Result findAllByHiple(@PathVariable("hiple") String hiple ,@PathVariable("user_id") Long userId){
		return new Result(placeService.findAllByHiple(hiple,userId));
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}

}
