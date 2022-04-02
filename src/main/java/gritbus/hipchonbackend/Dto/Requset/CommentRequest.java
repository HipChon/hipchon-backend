package gritbus.hipchonbackend.Dto.Requset;

import lombok.Data;

@Data
public class CommentRequest {
	private Long userId;
	private Long postId;
	private String detail;
}
