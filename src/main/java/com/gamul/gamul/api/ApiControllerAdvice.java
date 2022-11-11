package com.gamul.gamul.api;

import com.gamul.gamul.api.web.DefaultResponse;
import com.gamul.gamul.api.web.ResponseMessage;
import com.gamul.gamul.api.web.StatusCode;
import com.gamul.gamul.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler({LoginFailException.class, BadCredentialsException.class})
    public ResponseEntity loginFailException() {
        return new ResponseEntity(DefaultResponse.res(StatusCode.BAD_REQUEST, false, ResponseMessage.LOGIN_FAIL), HttpStatus.CREATED);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity duplicateEmailException(DuplicateEmailException e) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.CONFLICT, false, ResponseMessage.DUPLICATE_EMAIL), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDeniedException(AccessDeniedException e) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.FORBIDDEN, false, ResponseMessage.ACCESS_DENIED), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity unauthorizedException(UnauthorizedException e) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.UNAUTHORIZED, false, ResponseMessage.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.INVALID_ARGUMENTS, false, ResponseMessage.INVALID_ARGUMENTS), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateBookmarkException.class)
    public ResponseEntity duplicateBookmarkException(DuplicateBookmarkException e) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.BAD_REQUEST, false, ResponseMessage.DUPLICATE_BOOKMARK), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoBookmarkException.class)
    public ResponseEntity noBookmarkException(NoBookmarkException e) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.BAD_REQUEST, false, ResponseMessage.NOT_FOUND_BOOKMARK), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeException(RuntimeException e) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.INTERNAL_SERVER_ERROR, false, ResponseMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
