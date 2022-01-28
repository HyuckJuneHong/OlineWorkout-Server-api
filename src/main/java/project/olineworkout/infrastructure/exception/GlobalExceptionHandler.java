package project.olineworkout.infrastructure.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.olineworkout.domain.shared.ResponseFormat;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity badRequestException(BadRequestException exception) {
        ResponseFormat responseFormat = ResponseFormat.fail(exception.getMessage());
        return new ResponseEntity(responseFormat, HttpStatus.OK);
    }
}
