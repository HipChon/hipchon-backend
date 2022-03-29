package gritbus.hipchonbackend.Dto;

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
	public MyplaceDto(Long placeId, String name, String category, String address, String image, Long myplaceCnt,
		Long postCnt) {
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.address = address;
		this.image = image;
		this.myplaceCnt = myplaceCnt;
		this.postCnt = postCnt;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
