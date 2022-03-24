package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Hashtag;
import lombok.AllArgsConstructor;
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
