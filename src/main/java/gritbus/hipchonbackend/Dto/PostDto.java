package gritbus.hipchonbackend.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class PostDto {
	private Long id;
	private String userName;
	private String userImage;
	private LocalDate postTime;
	private String Nth; //이거 롬복에서 오류나는지 확인하기
	private List<String> imageList;
	private Long likeCnt;
	private Long commentCnt;
	private String detail;

	@QueryProjection
	public PostDto(Long id, String userName, String userImage, LocalDate postTime, String nth,
		List<String> imageList, Long likeCnt, Long commentCnt, String detail) {
		this.id = id;
		this.userName = userName;
		this.userImage = userImage;
		this.postTime = postTime;
		this.Nth = nth;
		this.imageList = imageList;
		this.likeCnt = likeCnt;
		this.commentCnt = commentCnt;
		this.detail = detail;
	}
}
