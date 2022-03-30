package gritbus.hipchonbackend.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import gritbus.hipchonbackend.Domain.LocalHipster.LocalHipster;
import lombok.Getter;

@Entity
@Getter
public class City {
	@Id
	@GeneratedValue
	@Column(name = "city_id")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Place> placeList = new ArrayList<>();

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<LocalHipster> LocalHipsterList = new ArrayList<>();

}
