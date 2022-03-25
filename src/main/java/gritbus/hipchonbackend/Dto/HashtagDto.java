package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class HashtagDto {
	private Long hasgtagId;
	private String name;
	private String image;

	@QueryProjection
	public HashtagDto(Long hasgtagId, String name, String image) {
		this.hasgtagId = hasgtagId;
		this.name = name;
		this.image = image;
	}
}
