package gritbus.hipchonbackend.Dto.LocalHipster;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalHipsterDto {

	private Long hipsterId;
	private String title;
	private List<LocalHipsterPostDto> postList = new ArrayList<>();

	@QueryProjection
	public LocalHipsterDto(Long hipsterId, String title) {
		this.hipsterId = hipsterId;
		this.title = title;
	}

	public void setPostList(List<LocalHipsterPostDto> postList) {
		this.postList = postList;
	}
}
