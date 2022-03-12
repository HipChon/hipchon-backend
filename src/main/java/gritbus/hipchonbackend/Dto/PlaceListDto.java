package gritbus.hipchonbackend.Dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Domain.KeywordReview;
import lombok.Data;

@Data
public class PlaceListDto {
	private Long id;
	private String name;
	private String category;
	private String city;
	private String placeImage;
	private Long postCnt;
	private Long myplaceCnt;
	private String keyword;
	private Boolean isMyplace;

	@QueryProjection
	public PlaceListDto(Long id, String name, String category, String city, String placeImage, Long commentCnt,
		Long myplaceCnt, String keyword, Boolean isMyplace) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.city = city;
		this.placeImage = placeImage;
		this.postCnt = commentCnt;
		this.myplaceCnt = myplaceCnt;
		this.keyword = keyword;
		this.isMyplace = isMyplace;
	}
}
