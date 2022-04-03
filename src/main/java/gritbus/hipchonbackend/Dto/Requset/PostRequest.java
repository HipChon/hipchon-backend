package gritbus.hipchonbackend.Dto.Requset;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PostRequest {
	private Long userId;
	private Long placeId;
	private List<Long> keywordIdList = new ArrayList<>();
	private String detail;
}
