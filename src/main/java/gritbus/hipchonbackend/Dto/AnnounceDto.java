package gritbus.hipchonbackend.Dto;

import java.time.LocalDateTime;

import gritbus.hipchonbackend.Domain.Announce;
import gritbus.hipchonbackend.Domain.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnnounceDto {
	private Long id;
	private String title;
	private LocalDateTime postTime;
	private String detail;

	public static AnnounceDto of (Announce a){
		return new AnnounceDto(
			a.getId(),
			a.getTitle(),
			a.getPostTime(),
			a.getDetail()
		);
	}
}
