package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gritbus.hipchonbackend.Dto.Post.BestPostDto;
import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Dto.Post.PostDto;
import gritbus.hipchonbackend.Dto.Requset.PostRequest;
import gritbus.hipchonbackend.Dto.UserDto;
import gritbus.hipchonbackend.Service.PostService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;

	@Operation(summary = "베스트 후기 API", description = "")
	@GetMapping("/best")
	public ResponseEntity<List<BestPostDto>> findAllByIsBest() {
		return ResponseEntity.ok(postService.findAllByIsBest());
	}

	@Operation(summary = "장소별 후기 API(장소 상세페이지)", description = "장소 상세페이지 아래에 들어가는 후기 피드용 API")
	@GetMapping("/place/{user_id}/{place_id}")
	public ResponseEntity<List<PostDto>> findByPlace(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(required = true, example = "1") @PathVariable("place_id") Long placeId) {
		return ResponseEntity.ok(postService.findByPlace(userId,placeId));
	}

	@Operation(summary = "본인이 올린 후기 API", description = "")
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<MypostDto>> findByUser(@Parameter(required = true, example = "1") @PathVariable("user_id") Long userid) {
		return ResponseEntity.ok(postService.findByUser(userid));
	}

	@Operation(summary = "모든 후기 API(피드 탭)", description = "피드 탭에 보여지는 모든 후기\norder에 like가 들어가면 좋아요순, recent는 최신순")
	@GetMapping("/all/{user_id}/{order}")
	public ResponseEntity<List<PostDto>> findAll(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(description = "order == like ? 좋아요순 : 최신순", required = true, example = "like") @PathVariable("order") String order) {
		return ResponseEntity.ok(postService.findAll(userId, order));
	}

	@Operation(summary = "후기 상세페이지 조회(베스트 후기에서 사용)", description = "")
	@GetMapping("/{user_id}/{post_id}")
	public ResponseEntity<PostDto> findOneById(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(required = true, example = "1") @PathVariable("post_id") Long postId) {
		return ResponseEntity.ok(postService.findOneById(userId, postId));
	}

	@Operation(summary = "후기작성", description = "form-data, json 형태 key는 post입니다\"\n"
		+ "{\n"
		+ " \"detail\": \"string\",\n"
		+ " \"keywordIdList\": [\n"
		+ " 0\n"
		+ " ],\n"
		+ " \"placeId\": 0,\n"
		+ " \"userId\": 0\n"
		+ "}")
	@PostMapping("")
	public ResponseEntity<Long> addPost(
		@ApiParam(value = "post") @RequestPart(value = "post") PostRequest post,
		@ApiParam(value = "file") @RequestPart(value = "file") List<MultipartFile> multipartFileList) {
		return ResponseEntity.ok(postService.add(post,multipartFileList));
	}

	@DeleteMapping("/{user_id}/{post_id}")
	public void delete(
		@PathVariable("user_id")Long userId,
		@PathVariable("post_id")Long postId){
		postService.delete(userId, postId);
	}


}
