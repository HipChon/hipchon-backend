package gritbus.hipchonbackend.Dto.Post;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CommentUserDto {
	private Long id;
	private String name;
	private String image;

	@QueryProjection
	public CommentUserDto(Long id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
	}
}
