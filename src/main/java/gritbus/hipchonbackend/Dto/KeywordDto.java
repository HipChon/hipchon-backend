package gritbus.hipchonbackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KeywordDto {
	private Long id;
	private String keyword;
	private String category;
	private Long postCnt;
}
