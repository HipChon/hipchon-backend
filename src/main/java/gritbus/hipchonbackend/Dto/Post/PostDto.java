package gritbus.hipchonbackend.Dto.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDto {
	private Long postId;
	private String time;
	private List<String> imageList;
	private Long likeCnt;
	private Long commentCnt;
	private String detail;
	private PostUserDto user;
	private Boolean isMypost;
	private PostPlaceSummaryDto place;

	@QueryProjection
	public PostDto(Long postId, LocalDateTime time, Long likeCnt, Long commentCnt,
		String detail, PostUserDto userDto,Boolean isMypost,PostPlaceSummaryDto placeDto) {
		this.postId = postId;
		this.time = time.toString();
		this.likeCnt = likeCnt;
		if (likeCnt == null) {
			this.likeCnt = 0L;
		}
		this.commentCnt = commentCnt;
		if (commentCnt == null) {
			this.likeCnt = 0L;
		}
		this.detail = detail;
		this.user = userDto;
		this.isMypost = isMypost;
		this.place = placeDto;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
}
