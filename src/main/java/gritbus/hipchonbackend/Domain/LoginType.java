package gritbus.hipchonbackend.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginType {
	kakao("kakao"),
	naver("naver"),
	apple("apple");

	private final String name;

}
