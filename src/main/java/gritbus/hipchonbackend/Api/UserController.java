package gritbus.hipchonbackend.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gritbus.hipchonbackend.Dto.UserDto;
import gritbus.hipchonbackend.Service.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;

	@Operation(summary = "로그인 API", description = "login_type에는 kakao, naver, apple 적으시면 됩니다!")
	@GetMapping("/login/{login_type}/{login_id}")
	public ResponseEntity<String> login(
		@PathVariable("login_type") String loginType,
		@PathVariable("login_id") String loginId
		) {
		return ResponseEntity.ok(userService.login(loginType,loginId));
	}

	@Operation(summary = "회원가입 API", description = "")
	@PostMapping("")
	public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.save(userDto));
	}

	@Operation(summary = "프로필 변경 API", description = "form-data, json형태 key는 user입니다"
		+ "{\n"
		+ "    \"loginId\" : String,\n"
		+ "    \"loginType\" : String,\n"
		+ "    \"name\" : String\n"
		+ "}"
		+ "\nloginId,loginType, 새로운 이름, 프로필 사진 만 넣어주셔도 됩니다\n기존에 있는 이름이면 에러를 일으킴(본인 이름이 또 똑같이 들어오는 경우는 에러 X)")
	@PutMapping("")
	public ResponseEntity<String> updateProfile(
		@ApiParam(value = "user") @RequestPart(value = "user") UserDto user,
		@ApiParam(value = "file") @RequestPart(value = "file") MultipartFile multipartFile) {
		return ResponseEntity.ok(userService.updateProfile(user,multipartFile));
	}

	@Operation(summary = "유저 정보 조회 API", description = "")
	@GetMapping("/{login_type}/{login_id}")
	public ResponseEntity<UserDto> getUserInfo(
		@PathVariable("login_type") String loginType,
		@PathVariable("login_id") String loginId
	) {
		return ResponseEntity.ok(userService.findByLoginTypeAndLoginId(loginType,loginId));
	}

	@Operation(summary = "유저 정보 삭제 API", description = "")
	@DeleteMapping("/{login_type}/{login_id}")
	public void deleteUser(
		@PathVariable("login_type") String loginType,
		@PathVariable("login_id") String loginId
	) {
		userService.deleteUser(loginType,loginId);
	}

}
