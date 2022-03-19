package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.MyplaceService;
import gritbus.hipchonbackend.Service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}
