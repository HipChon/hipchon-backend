package gritbus.hipchonbackend.Dto;

import java.time.LocalDateTime;

import gritbus.hipchonbackend.Domain.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventDto {
	private Long eventId;
	private String title;
	private String detail;
	private String thumbnail;
	private String time;
	private String url;

	public static EventDto of(Event e) {
		return new EventDto(
			e.getId(),
			e.getTitle(),
			e.getDetail(),
			e.getThumbnail(),
			e.getPostTime().toString(),
			e.getUrl()
		);
	}
}
