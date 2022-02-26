package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.HashtagService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HashtagAPiController {
	private final HashtagService hashtagService;

	@GetMapping("/api/hashtag/all")
	public Result findAll(){
		return new Result(hashtagService.findAll());
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}
