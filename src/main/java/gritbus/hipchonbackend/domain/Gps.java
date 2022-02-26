package gritbus.hipchonbackend.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Gps {

	private double latitude;
	private double longitude;

}
