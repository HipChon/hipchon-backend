package gritbus.hipchonbackend.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
	private Long postId;
	private Long userId;
	private String userName;
	private String userImage;
	private LocalDate postTime;
	private Long userPostCnt;
	private List<String> imageList;
	private Long likeCnt;
	private Long commentCnt;
	private String detail;
	private PostPlaceSummaryDto place;

	@QueryProjection
	public PostDto(Long postId, Long userId, String userName, String userImage, LocalDateTime postTime,
		Long userPostCnt,
		Long likeCnt, Long commentCnt, String detail, PostPlaceSummaryDto placeDto) {
		this.postId = postId;
		this.userId = userId;
		this.userName = userName;
		this.userImage = userImage;
		if (postTime != null) {
			this.postTime = postTime.toLocalDate();
		}
		this.userPostCnt = userPostCnt;
		this.likeCnt = likeCnt;
		if (likeCnt == null) {
			this.likeCnt = 0L;
		}
		this.commentCnt = commentCnt;
		this.detail = detail;
		this.place = placeDto;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
}
