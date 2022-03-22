package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MyplaceDto {
	private Long id;
	private String name;
	private String category;
	private String address;
	private String image;
	private Long myplaceCnt;
	private Long postCnt;
	private String memo;

	@QueryProjection
	public MyplaceDto(Long id, String name, String category, String address, String image, Long myplaceCnt,
		Long postCnt) {
		this.id = id;
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
