package gritbus.hipchonbackend.Domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class PlaceHashtag {
	@Id @GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hashtag_id")
	private Hashtag hashtag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id")
	private Place place;

	private void setHashtag(Hashtag hashtag){
		this.hashtag=hashtag;
		hashtag.getPlaceHashtagList().add(this);
	}

	private void setPlace(Place place){
		this.place=place;
		place.getPlaceHashtagList().add(this);
	}

	public static PlaceHashtag createPlaceHashtag(Place place, Hashtag hashtag){
		PlaceHashtag placeHashtag = new PlaceHashtag();
		placeHashtag.setPlace(place);
		placeHashtag.setHashtag(hashtag);
		return placeHashtag;
	}
}
