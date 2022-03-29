package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostPlaceSummaryDto {
	 private Long placeId;
	 private String name;
	 private String category;
	 private String address;
	 private Boolean isMyplace;
	 private String homepage;

	@QueryProjection
	public PostPlaceSummaryDto(Long placeId, String name, String category, String address, Boolean isMyplace,
		String homepage) {
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.address = address;
		this.isMyplace = isMyplace;
		this.homepage = homepage;
	}
}
