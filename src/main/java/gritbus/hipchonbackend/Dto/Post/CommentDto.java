package gritbus.hipchonbackend.Dto.Post;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import gritbus.hipchonbackend.Domain.PostComment;
import lombok.Data;

@Data
public class CommentDto {

	private Long commentId;
	private LocalDateTime time;
	private String detail;
	private CommentUserDto user;

	@QueryProjection
	public CommentDto(Long commentId,LocalDateTime time, String detail,CommentUserDto user) {
		this.commentId = commentId;
		this.time = time;
		this.detail = detail;
		this.user = user;
	}

}
