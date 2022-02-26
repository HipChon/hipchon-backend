package gritbus.hipchonbackend.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Place {
	@Id @GeneratedValue
	@Column(name= "place_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
	private List<PlaceHashtag> placeHashtagList = new ArrayList<>();

	@Embedded
	private Gps gps;

	@OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
	private List<Myplace> myplaceList = new ArrayList<>();


	private String name;
	private String address;
	private String placeImage;
	private String markerImage;

	private String contact;
	private String homepage;
	private String introduction;
	private String holiday;
	private String guide;
	private String oneLineIntro;

	private Long viewCnt;
	private Long like_cnt;

	private Boolean animal;
	private Long people;

	private Boolean hiple;

	public boolean containHashtag(Long hashtagId){
		List<Long> idList = placeHashtagList.stream()
			.map(PlaceHashtag::getHashtag)
			.map(Hashtag::getId)
			.collect(Collectors.toList());
		for(Long id : idList){
			if (id.equals(hashtagId)){
				return true;
			}
		}
		return false;
	}
}
