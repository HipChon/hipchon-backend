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
	private LocalDateTime postTime;
	private String detail;

	public static AnnounceDto of(Announce a) {
		return new AnnounceDto(
			a.getId(),
			a.getTitle(),
			a.getPostTime(),
			a.getDetail()
		);
	}
}
