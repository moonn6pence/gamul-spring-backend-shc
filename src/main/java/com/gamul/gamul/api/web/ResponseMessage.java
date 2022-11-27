package com.gamul.gamul.api.web;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";

    public static final String ACCESS_DENIED = "접근 제한되었습니다";
    public static final String UNAUTHORIZED = "인증에 실패했습니다";
    public static final String DUPLICATE_EMAIL = "이미 존재하는 이메일입니다";
    public static final String INVALID_ARGUMENTS = "유효하지 않은 인자입니다";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String REISSUE = "토큰 갱신 성공";

    public static final String READ_BOOKMARK = "북마크 조회 성공";

    public static final String CREATED_BOOKMARK = "북마크 추가 성공";

    public static final String DELETED_BOOKMARK = "북마크 삭제 성공";

    public static final String DUPLICATE_BOOKMARK = "이미 추가한 북마크입니다.";

    public static final String NOT_FOUND_BOOKMARK = "존재하지 않는 북마크입니다.";

    public static final String NOT_FOUND_MARKET = "존재하지 않는 마트입니다.";

    public static final String READ_PRICE_HISTORY = "물가 조회 성공";

}
