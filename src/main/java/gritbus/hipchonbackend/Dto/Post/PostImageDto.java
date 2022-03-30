package gritbus.hipchonbackend.Dto.Post;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class PostImageDto {
	private Long postId;
	private String image;

	@QueryProjection
	public PostImageDto(Long postId, String image) {
		this.postId = postId;
		this.image = image;
	}
}
