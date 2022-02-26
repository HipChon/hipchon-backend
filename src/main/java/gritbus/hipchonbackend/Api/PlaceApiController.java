package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlaceApiController {

	private final PlaceService placeService;

	@GetMapping("/api/place/{people}/{animal}/{city_id}/{hashtag_id}")
	public Result fastSearch(@PathVariable("people") Long people,
			@PathVariable("animal") Boolean animal,
			@PathVariable("city_id") Long cityId,
			@PathVariable("hashtag_id") Long hashtagId){
		return new Result(placeService.fastSearch(people,animal,cityId,hashtagId));
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}

}
