package gritbus.hipchonbackend.Dto;

import java.util.List;
import java.util.stream.Collectors;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.PlaceHashtag;
import gritbus.hipchonbackend.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class HipleDto {
	private Long id;
	private String name;
	private String city;
	private String placeImage;
	private Long people;
	private Long myplaceCnt;
	private Long postCnt;
	private Boolean isMyplace;

	public static HipleDto of (Place p, Long userId){
		return new HipleDto(
			p.getId(),
			p.getName(),
			p.getCity().getName(),
			p.getPlaceImage(),
			p.getPeople(),
			Long.valueOf(p.getMyplaceCount()),
			Long.valueOf(p.getMyplaceCount()),
			p.isMyplace(userId)
		);
	}
}

