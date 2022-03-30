package gritbus.hipchonbackend.Dto.LocalHipster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Dto.Post.PostPlaceSummaryDto;
import gritbus.hipchonbackend.Dto.Post.PostUserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalHipsterListDto {
	private Long id;
	private String city;
	private String title;
	private String summary;
	private String image;

	@QueryProjection
	public LocalHipsterListDto(Long id, String city, String title) {
		this.id = id;
		this.city = city;
		this.title = title;
	}
}
