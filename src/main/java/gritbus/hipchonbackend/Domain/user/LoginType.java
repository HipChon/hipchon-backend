package gritbus.hipchonbackend.Domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginType {
	kakao("카카오"),
	naver("네이버"),
	apple("애플");

	private final String name;

}
