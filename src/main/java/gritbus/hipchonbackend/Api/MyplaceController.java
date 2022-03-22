package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.MyplaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myplace")
public class MyplaceController {
	private final MyplaceService myplaceService;

	@PostMapping("/{user_id}/{place_id}")
	public Result addMyplace(@PathVariable("user_id") Long userId, @PathVariable("place_id")Long placeId){
		return new Result(myplaceService.add(userId,placeId));
	}

	@GetMapping("/{user_id}")
	public Result findAllMyplace(@PathVariable("user_id")Long userId){
		return new Result(myplaceService.findAllMyplace(userId));
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}
}
