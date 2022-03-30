package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Domain.Place;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class HipleDto {
	private Long placeId;
	private String name;
	private String category;
	private String city;
	private String placeImage;
	private Long postCnt;
	private Long myplaceCnt;
	private Boolean isMyplace;
	private KeywordDto keyword;

	@QueryProjection
	public HipleDto(Long placeId, String name, String city, String category, String placeImage, Long postCnt,
		Long myplaceCnt, Boolean isMyplace) {
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.city = city;
		this.placeImage = placeImage;
		this.myplaceCnt = myplaceCnt;
		if (myplaceCnt == null) {
			this.myplaceCnt = 0L;
		}
		this.postCnt = postCnt;
		if (postCnt == null) {
			this.postCnt = 0L;
		}
		this.isMyplace = isMyplace;
		if (isMyplace == null) {
			this.isMyplace = false;
		}
	}

}

