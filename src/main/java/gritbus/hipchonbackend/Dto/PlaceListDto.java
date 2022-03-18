package gritbus.hipchonbackend.Dto;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.annotations.QueryProjection;
import gritbus.hipchonbackend.Domain.Place;

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
	public PlaceListDto(Long id, String name, String category, String city, String placeImage, Long postCnt,
		Long myplaceCnt, String keyword,Boolean isMyplace) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.city = city;
		this.placeImage = placeImage;
		this.postCnt = postCnt;
		this.myplaceCnt = myplaceCnt;
		this.keyword = keyword;
		this.isMyplace = isMyplace;
	}

}
