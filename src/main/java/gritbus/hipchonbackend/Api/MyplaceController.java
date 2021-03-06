package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.MyplaceDto;
import gritbus.hipchonbackend.Service.MyplaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myplace")
public class MyplaceController {
	private final MyplaceService myplaceService;

	@Operation(summary = "마이플레이스에 저장 API", description = "중복시 오류\n저장 성공시 myplaceId 반환(프론트에서 당장은 사용할 일 없음)")
	@PostMapping("/{user_id}/{place_id}")
	public ResponseEntity<Long> addMyplace(@PathVariable("user_id") Long userId, @PathVariable("place_id") Long placeId) {
		return ResponseEntity.ok(myplaceService.add(userId, placeId));
	}

	@Operation(summary = "마이플레이스 삭제 API", description = "myplace 없을 때 오류")
	@DeleteMapping("/{user_id}/{place_id}")
	public void delete(@PathVariable("user_id") Long userId, @PathVariable("place_id") Long placeId) {
		myplaceService.delete(userId, placeId);
	}

	@Operation(summary = "마이플레이스 조회 API", description = "")
	@GetMapping("/{user_id}")
	public ResponseEntity<List<MyplaceDto>> findAllMyplace(@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId) {
		return ResponseEntity.ok(myplaceService.findAllMyplace(userId));
	}

	@Operation(summary = "마이플레이스 카테고리별 조회 API", description = "")
	@GetMapping("/{user_id}/{category_id}")
	public ResponseEntity<List<MyplaceDto>> findAllMyplaceByCategoryId(
		@Parameter(required = true, example = "1") @PathVariable("user_id") Long userId,
		@Parameter(required = true, example = "1") @PathVariable("category_id") Long categoryId) {
		return ResponseEntity.ok(myplaceService.findAllMyplaceByCategoryId(userId,categoryId));
	}

}
