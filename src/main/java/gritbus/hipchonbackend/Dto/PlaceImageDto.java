package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class PlaceImageDto {
	private Long placeId;
	private String image;

	@QueryProjection
	public PlaceImageDto(Long placeId, String image) {
		this.placeId = placeId;
		this.image = image;
	}
}
