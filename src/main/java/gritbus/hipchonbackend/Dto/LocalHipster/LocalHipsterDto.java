package gritbus.hipchonbackend.Dto.LocalHipster;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalHipsterDto {

	private Long id;
	private String title;
	private List<LocalHipsterPostDto> postList = new ArrayList<>();

	@QueryProjection
	public LocalHipsterDto(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public void setPostList(List<LocalHipsterPostDto> postList) {
		this.postList = postList;
	}
}
