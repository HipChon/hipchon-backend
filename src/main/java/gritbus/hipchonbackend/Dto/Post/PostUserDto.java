package gritbus.hipchonbackend.Dto.Post;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUserDto {
	private Long userId;
	private String name;
	private String image;
	private Long postCnt;

	@QueryProjection
	public PostUserDto(Long userId, String name, String image, Long postCnt) {
		this.userId = userId;
		this.name = name;
		this.image = image;
		this.postCnt = postCnt;
	}
}
