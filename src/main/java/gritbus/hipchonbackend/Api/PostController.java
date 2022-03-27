package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;

	@Operation(summary = "베스트 후기 API", description = "")
	@GetMapping("/best")
	public Result findAllByIsBest() {
		return new Result(postService.findAllByIsBest());
	}

	@Operation(summary = "장소별 후기 API(장소 상세페이지)", description = "장소 상세페이지 아래에 들어가는 후기 피드용 API")
	@GetMapping("/place/{place_id}")
	public Result findByPlace(@Parameter(required = true, example = "1") @PathVariable("place_id") Long placeId) {
		return new Result(postService.findByPlace(placeId));
	}

	@Operation(summary = "본인이 올린 후기 API", description = "")
	@GetMapping("/user/{user_id}")
	public Result findByUser(@Parameter(required = true, example = "1") @PathVariable("user_id") Long userid) {
		return new Result(postService.findByUser(userid));
	}

	@Operation(summary = "모든 후기 API(피드 탭)", description = "피드 탭에 보여지는 모든 후기\norder에 like가 들어가면 좋아요순, recent는 최신순")
	@GetMapping("/all/{user_id}/{order}")
	public Result findAll(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(description = "order == like ? 좋아요순 : 최신순", required = true, example = "like") @PathVariable("order") String order) {
		return new Result(postService.findAll(userId, order));
	}

	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}

}
