package project.olineworkout.infrastructure.exception;

public class JwtTokenExpiredException extends BusinessLogicException{

    public JwtTokenExpiredException() {
        super("jwt token expired");
    }
}
