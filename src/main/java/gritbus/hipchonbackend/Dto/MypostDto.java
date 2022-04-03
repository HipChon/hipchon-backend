package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MypostDto {
	private Long postId;
	private String name;
	private String image;

	@QueryProjection
	public MypostDto(Long postId, String name) {
		this.postId = postId;
		this.name = name;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
