package gritbus.hipchonbackend.Dto.Post;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CommentUserDto {
	private Long userId;
	private String name;
	private String image;

	@QueryProjection
	public CommentUserDto(Long userId, String name, String image) {
		this.userId = userId;
		this.name = name;
		this.image = image;
	}
}
