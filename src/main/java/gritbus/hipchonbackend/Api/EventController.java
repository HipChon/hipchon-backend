package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.EventService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
	private final EventService eventService;

	@GetMapping("/event")
	public Result findAll(){
		return new Result(eventService.findAll());
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}

}
