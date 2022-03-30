package gritbus.hipchonbackend.Domain.LocalHipster;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import gritbus.hipchonbackend.Domain.City;
import lombok.Getter;

@Entity
@Getter
public class LocalHipster {
	@Id
	@GeneratedValue
	@Column(name = "local_hipster_id")
	private Long id;

	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@OneToMany(mappedBy = "localHipster", cascade = CascadeType.ALL)
	private List<LocalHipsterPost> hipsterPostList = new ArrayList<>();


}
