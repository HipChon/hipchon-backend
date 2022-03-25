package gritbus.hipchonbackend.Dto;

import java.util.List;
import java.util.stream.Collectors;

import gritbus.hipchonbackend.Domain.Hashtag;
import gritbus.hipchonbackend.Domain.Place;
import gritbus.hipchonbackend.Domain.PlaceHashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlaceDto {
	//상세페이지
	// 맨 위 정보 화면
	private Long placeId;
	private String placeImage;
	private String name;
	private String contact;
	private int postCnt;
	private int myplaceCnt;
	private Boolean isMyplace;
	private String category;
	private String openTime;
	private String holiday;
	private String oneLineIntro;
	private String homepage;

	// 메뉴 들어가야 함

	//지도
	private String markerImage; //이거 필요한거 맞나?
	private Double latitude;
	private Double longitude;
	private String address;

	// 미정
	private String city;
	private Boolean hiple;
	private Boolean animal;
	private List<String> hashtag;

	public static PlaceDto of(Place p, Long userId) {
		return new PlaceDto(
			p.getId(),
			p.getPlaceImage(),
			p.getName(),
			p.getContact(),
			p.getPostCount(),
			p.getMyplaceCount(),
			p.isMyplace(userId),
			p.getCategory().getName(),
			p.getOpenTime(),
			p.getHoliday(),
			p.getOneLineIntro(),
			p.getHomepage(),
			//메뉴 추가 필요
			p.getMarkerImage(),
			p.getGps().getLatitude(),
			p.getGps().getLongitude(),
			p.getAddress(),

			p.getCity().getName(),
			p.getHiple(),
			p.getAnimal(),
			p.getPlaceHashtagList().stream()
				.map(PlaceHashtag::getHashtag)
				.map(Hashtag::getName)
				.collect(Collectors.toList())
		);
	}

}
