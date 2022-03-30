package gritbus.hipchonbackend.Domain.LocalHipster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import gritbus.hipchonbackend.Domain.Place;
import lombok.Getter;

@Entity
@Getter
public class LocalHipsterPostImage {
	@Id
	@GeneratedValue
	@Column(name = "loacl_hipster_post_image_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_hipster_post_id")
	private LocalHipsterPost localHipsterPost;

	private String image;

}
