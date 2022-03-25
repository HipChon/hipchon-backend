package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.KeywordReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keyword")
public class KeywordReviewController {
	private final KeywordReviewService keywordReviewService;

	@Operation(summary = "해당 장소의 키워드 Top3(상세페이지)", description = "장소 상세페이지의 키워드 부분용 API입니다")
	@GetMapping("/top3/{place_id}")
	public Result getTop3(@Parameter(required = true, example = "1") @PathVariable("place_id") Long placeId) {
		return new Result(keywordReviewService.getTop3(placeId));
	}

	@Operation(summary = "해당 장소의 키워드 Top1)", description = "지금 프론트에서 쓰이진 않습니다")
	@GetMapping("/top1/{place_id}")
	public Result getTop1(@Parameter(required = true, example = "1") @PathVariable("place_id") Long placeId) {
		return new Result(keywordReviewService.getTop1(placeId));
	}

	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}
}
