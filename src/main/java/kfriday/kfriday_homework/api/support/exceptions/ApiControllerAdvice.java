package kfriday.kfriday_homework.api.support.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

import kfriday.kfriday_homework.api.support.response.BaseResponse;
import kfriday.kfriday_homework.api.support.response.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler(BaseException.class)
	public BaseResponse<BaseResponseStatus> baseExceptionHandle(BaseException exception) {
		log.warn("BaseException. error message: {}", exception.getMessage());
		return new BaseResponse<>(exception.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public BaseResponse<BaseResponseStatus> exceptionHandle(Exception exception) {
		log.error("Exception has occured. ", exception);
		return new BaseResponse<>(BaseResponseStatus.UNEXPECTED_ERROR);
	}
}
