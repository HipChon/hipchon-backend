package gritbus.hipchonbackend.Dto;

import gritbus.hipchonbackend.Domain.City;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CityDto {
	private Long cityId;
	private String name;

	public static CityDto of(City c) {
		return new CityDto(
			c.getId(),
			c.getName()
		);
	}
}
