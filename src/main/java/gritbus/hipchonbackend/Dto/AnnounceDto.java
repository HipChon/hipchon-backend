package gritbus.hipchonbackend.Dto;

import java.time.LocalDateTime;

import gritbus.hipchonbackend.Domain.Announce;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnnounceDto {
	private Long announceId;
	private String title;
	private String time;
	private String detail;
	private String thumbnail;
	private String url;

	public static AnnounceDto of(Announce a) {
		return new AnnounceDto(
			a.getId(),
			a.getTitle(),
			a.getPostTime().toString(),
			a.getDetail(),
			a.getThumbnail(),
			a.getUrl()
		);
	}
}
