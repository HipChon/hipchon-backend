package gritbus.hipchonbackend.Domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter

public class Announce {
	@Id
	@GeneratedValue
	@Column(name = "announce_id")
	private Long id;
	private String title;
	private LocalDateTime postTime;
	private String detail;
	private String thumbnail;
}
