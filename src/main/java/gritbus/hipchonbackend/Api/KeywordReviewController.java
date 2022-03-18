package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.KeywordReviewService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keyword")
public class KeywordReviewController {
	private final KeywordReviewService keywordReviewService;

	@GetMapping("/top3/{place_id}")
	public Result getTop3(@PathVariable("place_id") Long placeId){
		return new Result(keywordReviewService.getTop3(placeId));
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}
