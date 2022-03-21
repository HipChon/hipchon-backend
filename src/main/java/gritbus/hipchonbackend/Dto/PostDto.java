package gritbus.hipchonbackend.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
public class PostDto {
	private Long id;
	private Long userId;
	private String userName;
	private String userImage;
	private LocalDate postTime;
	private Long userPostCnt;
	private List<String> imageList;
	private Long likeCnt;
	private Long commentCnt;
	private String detail;
	//imggeList는 나중에 넣기
	@QueryProjection
	public PostDto(Long id, Long userId,String userName, String userImage, LocalDateTime postTime, Long userPostCnt,
		Long likeCnt, Long commentCnt, String detail) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.userImage = userImage;
		this.postTime = postTime.toLocalDate();
		this.userPostCnt = userPostCnt;
		this.likeCnt = likeCnt;
		this.commentCnt = commentCnt;
		this.detail = detail;
	}



	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
}