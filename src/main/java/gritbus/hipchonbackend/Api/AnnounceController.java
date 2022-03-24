package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.AnnounceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/announce")

public class AnnounceController {
	private final AnnounceService announceService;

	@Operation(summary = "공지조회 API", description = "데이터 없어서 조회 안됩니다 일단 다른거 하고 넣겠습니다!")
	@GetMapping("")
	public Result findAll(){
		return new Result(announceService.findAll());
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}

