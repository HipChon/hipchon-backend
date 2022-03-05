package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.MyplaceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MyplaceController {
	private final MyplaceService myplaceService;

	@PostMapping("/myplace/{user_id}/{place_id}")
	public Long addMyplace(@PathVariable("user_id") Long userId, @PathVariable("place_id")Long placeId){
		return myplaceService.add(userId,placeId);
	}
}
