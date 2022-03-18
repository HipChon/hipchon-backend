package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class KeywordDto {
	private Long id;
	private String keyword;
	// private String category;
	private Long postCnt;

	@QueryProjection
	public KeywordDto(Long id, String keyword,  Long postCnt) {
		this.id = id;
		this.keyword = keyword;
		// this.category = category;
		this.postCnt = postCnt;
	}
}
