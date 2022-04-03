package gritbus.hipchonbackend.Dto.LocalHipster;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalHipsterListDto {
	private Long hipsterId;
	private String city;
	private String title;
	private String summary;
	private String image;

	@QueryProjection
	public LocalHipsterListDto(Long hipsterId, String city, String title) {
		this.hipsterId = hipsterId;
		this.city = city;
		this.title = title;
	}
}
