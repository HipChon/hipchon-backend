package gritbus.hipchonbackend.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gritbus.hipchonbackend.Domain.user.User;
import lombok.Getter;

@Entity
@Getter
public class Myplace {
	@Id
	@GeneratedValue
	@Column(name = "myplace_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id")
	private Place place;

	public static Myplace createMyplace(User user, Place place) {
		Myplace myplace = new Myplace();
		myplace.setPlace(place);
		myplace.setUser(user);
		return myplace;
	}

	private void setPlace(Place place) {
		this.place = place;
		place.getMyplaceList().add(this);
	}

	private void setUser(User user) {
		this.user = user;
		user.getMyplaceList().add(this);
	}

}
