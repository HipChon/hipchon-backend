package gritbus.hipchonbackend.domain;

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

@Entity
@Getter
public class Place {
	@Id @GeneratedValue
	@Column(name= "place_id")
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	private String address;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Embedded
	private Gps gps;

	private String placeImage;
	private String markerImage;

	private String contact;
	private String homepage;
	private String introduction;
	private String holiday;
	private String guide;
	private String oneline;

	private Long viewCnt;
	private Long like;

	private boolean animal;
	private Long people;

	private boolean hiple;
}
