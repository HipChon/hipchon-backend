package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.CityDto;
import gritbus.hipchonbackend.Service.CityService;
import gritbus.hipchonbackend.Service.HashtagService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CityApiController {
	private final CityService cityService;

	@GetMapping("/city/all")
	public Result findAll(){
		return new Result(cityService.findAll());
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}
