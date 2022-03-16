package gritbus.hipchonbackend.Dto;

import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Domain.City;
import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.PlaceHashtag;
import gritbus.hipchonbackend.Domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class HipleDto {
	private Long id;
	private String name;
	private String category;
	private String city;
	private String placeImage;
	private Long postCnt;
	private Long myplaceCnt;
	private Boolean isMyplace;

	@QueryProjection
	public HipleDto(Long id, String name, String city, String category,String placeImage, Long postCnt, Long myplaceCnt,
		Boolean isMyplace) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.city = city;
		this.placeImage = placeImage;
		this.myplaceCnt = myplaceCnt;
		this.postCnt = postCnt;
		this.isMyplace = isMyplace;
	}

	public static HipleDto of (Place p, Long userId){
		return new HipleDto(
			p.getId(),
			p.getName(),
			p.getCategory().getName(),
			p.getCity().getName(),
			p.getPlaceImage(),
			Long.valueOf(p.getPostCount()),
			Long.valueOf(p.getMyplaceCount()),
			p.isMyplace(userId)
		);
	}
}

