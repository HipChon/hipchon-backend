package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.MypostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/mypost")
public class MypostController {
	private final MypostService mypostService;

	@GetMapping("/{user_id}")
	public Result findMypost(@PathVariable("user_id")Long userId){
		return new Result(mypostService.findMypost(userId));
	}

	@Data
	@AllArgsConstructor
	static class Result<T>{
		private T data;
	}


}
