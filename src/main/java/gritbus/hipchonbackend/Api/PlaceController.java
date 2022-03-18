package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping("/place/{user_id}/{city_id}/{category_id}/{order}")
	public Result fastSearch(
			@PathVariable("user_id") Long userId,
			@PathVariable("city_id") Long cityId,
			@PathVariable("category_id") Long categoryId,
			@PathVariable("order") String order){
		return new Result(placeService.fastSearch(userId,cityId,categoryId,order));
	}

	@GetMapping("/place/{user_id}/{hashtag_id}")
	public Result findByHashtag(@PathVariable("hashtag_id") Long hashtagId,@PathVariable("user_id") Long userId){
		return new Result(placeService.findByHashtag(hashtagId,userId));
	}

	@GetMapping("/place/hiple/{user_id}")
	public Result findAllByHiple(@PathVariable("user_id") Long userId){
		return new Result(placeService.findAllByHiple(userId));
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}

}
