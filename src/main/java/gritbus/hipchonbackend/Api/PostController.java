package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;

	@GetMapping("/best")
	public Result findAllByIsBest(){
		return new Result(postService.findAllByIsBest());
	}

	@GetMapping("/{place_id}")
	public Result findByPlace(@PathVariable("place_id") Long placeId){
		return new Result(postService.findByPlace(placeId));
	}

	@GetMapping("/all/{order}")
	public Result findAll(@PathVariable("order")String order){
		return new Result(postService.findAll(order));
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}

}
