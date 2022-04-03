package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.Post.CommentDto;
import gritbus.hipchonbackend.Dto.Post.MyCommentDto;
import gritbus.hipchonbackend.Dto.Requset.CommentRequest;
import gritbus.hipchonbackend.Service.PostCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postcomment")
public class PostCommentController {
	private final PostCommentService postCommentService;

	@Operation(summary = "게시글의 댓글을 보여줍니다", description = "")
	@GetMapping("/{post_id}")
	public ResponseEntity<List<CommentDto>> findAllByPostId(
		@Parameter(required = true, example = "1") @PathVariable("post_id") Long postId){
		return ResponseEntity.ok(postCommentService.findALlByPostId(postId));
	}

	@Operation(summary = "댓글 작성 API", description = "")
	@PostMapping("")
	public ResponseEntity<Long> addMypost(@RequestBody CommentRequest commentRequest) {
		return ResponseEntity.ok(postCommentService.add(commentRequest));
	}

	@Operation(summary = "댓글 삭제 API", description = "댓글 존재하지 않을 시")
	@DeleteMapping("/{comment_id}")
	public void delete(@PathVariable("comment_id") Long commentId) {
		postCommentService.delete(commentId);
	}

	@Operation(summary = "내 댓글 조회 API", description = "")
	@GetMapping("/mycomment/{user_id}")
	public ResponseEntity<List<MyCommentDto>>  findAllMycomment(@PathVariable("user_id") Long userId) {
		return ResponseEntity.ok(postCommentService.findAllMycomment(userId));
	}
}
