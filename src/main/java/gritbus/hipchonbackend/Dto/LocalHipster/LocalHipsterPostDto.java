package gritbus.hipchonbackend.Dto.LocalHipster;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Dto.Post.PostPlaceSummaryDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalHipsterPostDto {
	private Long id;
	private String title;
	private String detail;
	private PostPlaceSummaryDto place;
	private List<String> imageList;

	@QueryProjection
	public LocalHipsterPostDto(Long id, String title, String detail,
		PostPlaceSummaryDto place) {
		this.id = id;
		this.title = title;
		this.detail = detail;
		this.place = place;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
}
