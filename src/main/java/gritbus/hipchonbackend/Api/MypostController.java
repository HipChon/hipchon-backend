package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.MypostDto;
import gritbus.hipchonbackend.Service.MypostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/mypost")
public class MypostController {
	private final MypostService mypostService;

	@Operation(summary = "내가 좋아요 누른 후기 API", description = "")
	@GetMapping("/{user_id}")
	public ResponseEntity<List<MypostDto>> findMypost(@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId) {
		return ResponseEntity.ok(mypostService.findMypost(userId));
	}

	@Operation(summary = "좋아요 API", description = "중복시 오류 반환\n저장 성공시 mypost 반환(프론트에서 당장은 사용할 일 없음)")
	@PostMapping("/{user_id}/{post_id}")
	public ResponseEntity<Long> addMypost(@PathVariable("user_id") Long userId, @PathVariable("post_id") Long postId) {
		return ResponseEntity.ok(mypostService.add(userId, postId));
	}

	@Operation(summary = "좋아요 삭제 API", description = "좋아요 하지 않았을 때 오류 반환")
	@DeleteMapping("/{user_id}/{post_id}")
	public void delete(@PathVariable("user_id") Long userId, @PathVariable("post_id") Long postId) {
		mypostService.delete(userId, postId);
	}
}
