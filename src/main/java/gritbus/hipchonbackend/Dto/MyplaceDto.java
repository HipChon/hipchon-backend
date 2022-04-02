package gritbus.hipchonbackend.Dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyplaceDto {
	private Long placeId;
	private String name;
	private String category;
	private String address;
	private String image;
	private Long myplaceCnt;
	private Long postCnt;
	private String memo;

	@QueryProjection
	public MyplaceDto(Long placeId, String name, String category, String address,Long myplaceCnt,
		Long postCnt) {
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.address = address;
		this.myplaceCnt = myplaceCnt;
		this.postCnt = postCnt;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
