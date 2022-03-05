package gritbus.hipchonbackend.Domain;

import java.time.LocalDateTime;
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
public class Event {
	@Id @GeneratedValue
	@Column(name = "event")
	private Long id;
	private String title;
	private LocalDateTime postTime;
	private String detail;
	private String thumbnail;

	@OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
	private List<EventImage> eventImageList = new ArrayList<>();

}
