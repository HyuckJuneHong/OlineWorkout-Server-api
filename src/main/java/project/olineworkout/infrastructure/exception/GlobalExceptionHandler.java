package project.olineworkout.infrastructure.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.olineworkout.domain.shared.ResponseFormat;

@RestControllerAdvice //예외가 터졌을 때 이쪽으로 오도록 하는 어노테이션
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseFormat<Void> notFoundExceptionHandler(NotFoundException notFoundException){
        return ResponseFormat.fail(notFoundException.getMessage());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseFormat<Void> badRequestExcetpionHandler(BadRequestException badRequestException){
        return ResponseFormat.fail(badRequestException.getMessage());
    }

    /**
     * To do...
     */
}
