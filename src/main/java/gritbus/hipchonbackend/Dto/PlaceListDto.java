package gritbus.hipchonbackend.Dto;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.annotations.QueryProjection;
import gritbus.hipchonbackend.Domain.Place;

import lombok.Data;

@Data
public class PlaceListDto {
	private Long placeId;
	private String name;
	private String category;
	private String city;
	private String placeImage;
	private Long postCnt;
	private Long myplaceCnt;
	private String keyword;
	private String keywordEmoji;
	private String keywordCategory;
	private Boolean isMyplace;

	@QueryProjection
	public PlaceListDto(Long placeId, String name, String category, String city, String placeImage, Long postCnt,
		Long myplaceCnt,Boolean isMyplace) {
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.city = city;
		this.placeImage = placeImage;
		this.postCnt = postCnt;
		if (postCnt==null){
			this.postCnt = 0L;
		}
		this.myplaceCnt = myplaceCnt;
		if (myplaceCnt==null){
			this.myplaceCnt = 0L;
		}
		this.keyword = "키워드 리뷰가 없습니다!";
		this.keywordEmoji = "";
		this.keywordCategory = "";
		this.isMyplace = isMyplace;
		if (isMyplace==null){
			this.isMyplace = false;
		}
	}

}
