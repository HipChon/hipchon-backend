package gritbus.hipchonbackend.Dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class PlaceListDto {
	private Long placeId;
	private String name;
	private String category;
	private String city;
	private Long postCnt;
	private Long myplaceCnt;
	private Boolean isMyplace;
	private KeywordDto keyword;
	private List<String> imageList;

	@QueryProjection
	public PlaceListDto(Long placeId, String name, String category, String city, Long postCnt,
		Long myplaceCnt, Boolean isMyplace) {
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.city = city;
		this.postCnt = postCnt;
		if (postCnt == null) {
			this.postCnt = 0L;
		}
		this.myplaceCnt = myplaceCnt;
		if (myplaceCnt == null) {
			this.myplaceCnt = 0L;
		}
		this.isMyplace = isMyplace;
		if (isMyplace == null) {
			this.isMyplace = false;
		}
	}
}
