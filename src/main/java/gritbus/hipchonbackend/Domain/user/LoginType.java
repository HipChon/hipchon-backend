package gritbus.hipchonbackend.Domain.user;

public enum LoginType {
	kakao("카카오"),
	naver("네이버"),
	apple("애플");

	private final String name;

	LoginType(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
