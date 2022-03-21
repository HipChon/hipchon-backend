package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class KeywordDto {
	private Long id;
	private String keyword;
	private String category;
	private String emoji;
	private Long postCnt;

	@QueryProjection
	public KeywordDto(Long id, String keyword, String category,String emoji, Long postCnt) {
		this.id = id;
		this.keyword = keyword;
		this.category = category;
		this.emoji = emoji;
		this.postCnt = postCnt;
		if (postCnt==null){
			this.postCnt = 0L;
		}
	}
}
