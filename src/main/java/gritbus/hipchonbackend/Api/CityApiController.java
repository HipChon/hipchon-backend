package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.CityDto;
import gritbus.hipchonbackend.Service.CityService;
import gritbus.hipchonbackend.Service.HashtagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityApiController {
	private final CityService cityService;

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}
