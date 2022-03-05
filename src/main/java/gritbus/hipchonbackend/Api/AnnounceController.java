package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.AnnounceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AnnounceController {
	private final AnnounceService announceService;

	@GetMapping("/announce")
	public Result findAll(){
		return new Result(announceService.findAll());
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}

