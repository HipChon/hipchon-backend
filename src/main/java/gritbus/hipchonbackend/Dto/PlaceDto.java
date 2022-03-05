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
	private Long id;
	private String name;
	private String category;
	private String city;
	private String address;
	private Double latitude;
	private Double longitude;
	private String placeImage;
	private String markerImage;
	private String contact;
	private String homepage;
	private String holiday;
	private Long viewCnt;
	private Long likeCnt;
	private Boolean hiple;
	private Long people;
	private Boolean animal;
	private String oneLineIntro;
	private String guide;
	private String introduction;
	private List<String> hashtag;
	private Boolean isMyplace;

	public static PlaceDto of (Place p,Long userId){
		return new PlaceDto(
			p.getId(),
			p.getName(),
			p.getCategory().getName(),
			p.getCity().getName(),
			p.getAddress(),
			p.getGps().getLatitude(),
			p.getGps().getLongitude(),
			p.getPlaceImage(),
			p.getMarkerImage(),
			p.getContact(),
			p.getHomepage(),
			p.getHoliday(),
			p.getViewCnt(),
			p.getLike_cnt(),
			p.getHiple(),
			p.getPeople(),
			p.getAnimal(),
			p.getOneLineIntro(),
			p.getGuide(),
			p.getIntroduction(),
			p.getPlaceHashtagList().stream()
				.map(PlaceHashtag::getHashtag)
				.map(Hashtag::getName)
				.collect(Collectors.toList()),
			p.isMyplace(userId)
		);
	}

}
