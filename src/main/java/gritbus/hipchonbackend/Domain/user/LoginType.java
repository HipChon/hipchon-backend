package gritbus.hipchonbackend.Domain.user;

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
