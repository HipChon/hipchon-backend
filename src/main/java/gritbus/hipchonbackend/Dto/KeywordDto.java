package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class KeywordDto {
	private Long keywordId;
	private String keyword;
	private String category;
	private String emoji;
	private Long postCnt;

	@QueryProjection
	public KeywordDto(Long keywordId, String keyword, String category, String emoji, Long postCnt) {
		this.keywordId = keywordId;
		this.keyword = keyword;
		this.category = category;
		this.emoji = emoji;
		this.postCnt = postCnt;
		if (postCnt == null) {
			this.postCnt = 0L;
		}
	}
}
