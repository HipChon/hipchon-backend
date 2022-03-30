package gritbus.hipchonbackend.Dto.Post;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUserDto {
	private Long userId;
	private String userName;
	private String userImage;
	private Long userPostCnt;

	@QueryProjection
	public PostUserDto(Long userId, String userName, String userImage, Long userPostCnt) {
		this.userId = userId;
		this.userName = userName;
		this.userImage = userImage;
		this.userPostCnt = userPostCnt;
	}
}
