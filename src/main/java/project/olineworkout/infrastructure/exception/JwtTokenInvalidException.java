package project.olineworkout.infrastructure.exception;

public class JwtTokenInvalidException extends UserDefineException{

    public JwtTokenInvalidException(String message) {
        super(message);
    }
}
