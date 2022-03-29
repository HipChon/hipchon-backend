package gritbus.hipchonbackend.Api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.UserDto;
import gritbus.hipchonbackend.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;

	@Operation(summary = "로그인 API", description = "login_type에는 kakao, naver, apple 적으시면 됩니다!")
	@GetMapping("/{login_type}/{login_id}")
	public ResponseEntity<Long> login(
		@PathVariable("login_type") String loginType,
		@PathVariable("login_id") Long loginId
		) {
		return ResponseEntity.ok(userService.login(loginType,loginId));
	}

	@Operation(summary = "회원가입 API", description = "login_type에는 kakao, naver, apple 적으시면 됩니다!")
	@PostMapping("")
	public ResponseEntity<Long> signUp(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.save(userDto));
	}

}
