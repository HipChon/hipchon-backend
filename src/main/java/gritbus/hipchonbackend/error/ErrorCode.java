package gritbus.hipchonbackend.error;


import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	// /* 400 BAD_REQUEST : 잘못된 요청 */
	// INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
	// MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
	// CANNOT_FOLLOW_MYSELF(BAD_REQUEST, "자기 자신은 팔로우 할 수 없습니다"),
	//
	// /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
	// INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
	// UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),
	//
	// /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
	// MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
	// REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),
	// NOT_FOLLOW(NOT_FOUND, "팔로우 중이지 않습니다"),
	//
	// /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
	// DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),


	UNAUTHORIZED_USER(401,"USER-401","등록된 사용자가 아닙니다"),
	INVALID_USER_ACTION(401, "USER-401","권한이 없는 사용자입니다"),
	USER_DUPLICATION(409,"USER-409","존재하는 사용자입니다"),
	ELEMENT_DUPLICATION(409,"ELEMENT-409","존재하는 요소입니다"),
	USER_NAME_DUPLICATION(409,"USER-NAME-409","존재하는 이름 입니다"),
	USER_EMAIL_DUPLICATION(409,"USER-EMAIL-409","존재하는 이메일 입니다"),
	PLACE_NOT_FOUND(404, "PLACE-404","존재하지 않는 장소입니다"),
	POST_NOT_FOUND(404, "POST-404","존재하지 않는 후기입니다"),
	KEYWORDREVIEW_NOT_FOUND(404, "KEYWORDREVIEW-404","존재하지 않는 키워드 후기 입니다"),
	ELEMENT_NOT_FOUND(404, "ELEMENT-404","존재하지 않는 요소입니다"),
	FILE_BAD_REQUEST(400,"FILE-400", "업로드에 실패하였습니다");

	private int status;
	private String errorCode;
	private String message;


}
