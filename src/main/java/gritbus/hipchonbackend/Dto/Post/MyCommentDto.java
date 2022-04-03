package gritbus.hipchonbackend.Dto.Post;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MyCommentDto {
	private Long commentId;
	private LocalDateTime time;
	private String detail;
	private PostImageDto post;

	@QueryProjection
	public MyCommentDto(Long commentId,LocalDateTime time, String detail,PostImageDto post) {
		this.commentId = commentId;
		this.time = time;
		this.detail = detail;
		this.post = post;
	}

	public void setPost(PostImageDto post) {
		this.post = post;
	}
}
