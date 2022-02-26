package gritbus.hipchonbackend.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class Category {
	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;

	private String name;

	@OneToMany(mappedBy ="category",cascade = CascadeType.ALL)
	private List<Place> placeList = new ArrayList<>();

}
