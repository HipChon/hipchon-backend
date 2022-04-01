package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.Post.CommentDto;
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



}
