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

	@OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
	private List<Myplace> myplaceList = new ArrayList<>();

	@OneToMany(mappedBy = "place" , cascade = CascadeType.ALL)
	private List<Post> postList = new ArrayList<>();

	@Embedded
	private Gps gps;

	private String name;
	private String address;

	private String placeImage;
	private String markerImage;

	private String contact;
	private String homepage;
	private String holiday;
	private String openTime;
	private String oneLineIntro;
	private Boolean animal;

	private Long viewCnt = 0L;
	private Boolean hiple;

	public boolean containHashtag(Long hashtagId){
		List<Long> idList = placeHashtagList.stream()
			.map(PlaceHashtag::getHashtag)
			.map(Hashtag::getId)
			.collect(Collectors.toList());
		return isInList(idList,hashtagId);
	}

	public int getPostCount(){
		return postList.size();
	}

	public int getMyplaceCount(){
		return myplaceList.size();
	}

	public boolean isMyplace(Long userId){
		List<Long> idList = myplaceList.stream()
			.map(Myplace::getUser)
			.map(User::getId)
			.collect(Collectors.toList());
		return isInList(idList,userId);

	}

	private boolean isInList(List<Long> idList,Long target){
		for(Long id : idList){
			if (id.equals(target)){
				return true;
			}
		}
		return false;
	}
}
