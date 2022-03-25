package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MypostDto {
	private Long postId;
	private String placeName;
	private String postImage;

	@QueryProjection
	public MypostDto(Long postId, String placeName) {
		this.postId = postId;
		this.placeName = placeName;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}
}
