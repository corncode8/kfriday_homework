package kfriday.kfriday_homework.api.support.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {

	/**
	 * 200 : 요청 성공
	 */
	SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),

	/**
	 * 400 : Request, Response 오류
	 */

	NOT_FIND_SHIPMENT(false, HttpStatus.NOT_FOUND.value(), "일치하는 Shipment가 없습니다."),

	/**
	 * 500 :  Database, Server 오류
	 */
	DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 연결에 실패하였습니다."),
	DATABASE_EMPTY(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "저장된 데이터가 없습니다."),
	SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버와의 연결에 실패하였습니다."),

	UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다.");

	private final boolean isSuccess;
	private final int code;
	private final String message;

	private BaseResponseStatus(boolean isSuccess, int code, String message) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}
}
