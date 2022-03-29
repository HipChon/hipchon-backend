package gritbus.hipchonbackend.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Service.HashtagService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hashtag")
public class HashtagController {
	private final HashtagService hashtagService;

}
