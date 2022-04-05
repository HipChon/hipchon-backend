package gritbus.hipchonbackend.Dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MenuDto {
	private Long menuId;
	private String name;
	private Long price;
	private String image;

	@QueryProjection
	public MenuDto(Long menuId, String name, Long price, String image) {
		this.menuId = menuId;
		this.name = name;
		this.price = price;
		this.image = image;
	}
}
