package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class HipleDto {
	private Long placeId;
	private String name;
	private String category;
	private String city;
	private String image;
	private Long postCnt;
	private Long myplaceCnt;
	private Boolean isMyplace;
	private KeywordDto keyword;

	@QueryProjection
	public HipleDto(Long placeId, String name, String category, String city,Long postCnt,
		Long myplaceCnt, Boolean isMyplace) {
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.city = city;
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

	public void setImage(String image) {
		this.image = image;
	}
}

