package gritbus.hipchonbackend.Dto;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HashtagDto {
	private Long id;
	private String name;

	public static HashtagDto of (Hashtag h){
		return new HashtagDto(
			h.getId(),
			h.getName()
		);
	}
}
