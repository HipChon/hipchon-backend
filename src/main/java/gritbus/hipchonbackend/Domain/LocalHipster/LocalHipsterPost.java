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

import gritbus.hipchonbackend.Domain.Place;
import lombok.Getter;

@Entity
@Getter
public class LocalHipsterPost {
	@Id
	@GeneratedValue
	@Column(name = "local_hipster_post_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_hipster_id")
	private LocalHipster localHipster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id")
	private Place place;

	@OneToMany(mappedBy = "localHipsterPost", cascade = CascadeType.ALL)
	private List<LocalHipsterPostImage> hipsterPostImageList = new ArrayList<>();

	private String title;
	private String detail;

}
