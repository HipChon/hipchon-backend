package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
