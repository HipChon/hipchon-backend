package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {
	private final EventService eventService;

	@Operation(summary = "이벤트 조회 API", description = "이벤트 정보가 없어서 사용안됩니다!")
	@GetMapping("")
	public Result findAll() {
		return new Result(eventService.findAll());
	}

	@Data
	@AllArgsConstructor
	static class Result<T> {
		private T data;
	}

}
