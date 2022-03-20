package gritbus.hipchonbackend.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class User {
	@Id @GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String name;
	private String profileImage;
	private String email;


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> postList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Myplace> myplaceList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> commentList = new ArrayList<>();



}
