package dev.memocode.question_server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static dev.memocode.question_server.exception.GlobalErrorCodeType.CRITICAL;
import static dev.memocode.question_server.exception.GlobalErrorCodeType.INFO;
import static org.springframework.http.HttpStatus.*;

@Getter
public enum GlobalErrorCode {

    INTERNAL_ERROR(INTERNAL_SERVER_ERROR, 500, "서버 에러, 관리자에게 문의하세요", CRITICAL),
    UNEXPECTED_API_RESPONSE(BAD_GATEWAY, 502, "예상치 못한 API 응답입니다.", CRITICAL),
    ACCOUNT_ID_CLAIM_NAME(NOT_FOUND, 404, "계정이 존재하지 않습니다.", INFO)
    ;

    private final HttpStatus status;
    private final int code;
    private final String message;
    private final GlobalErrorCodeType type;

    GlobalErrorCode(HttpStatus status, int code, String message, GlobalErrorCodeType type) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.type = type;
    }
}
