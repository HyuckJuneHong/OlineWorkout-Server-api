package project.olineworkout.domain.shared;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseFormat<T> {

    @ApiModelProperty(example = "성공은 1 or 실패는 2 or 토큰 만료는 3")
    private int code;
    @ApiModelProperty(example = "true or false")
    private Boolean check;
    @ApiModelProperty(example = "성공 or 에러메시지")
    private String description;
    private T data;

    public static ResponseFormat ok(){

        return ResponseFormat.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .check(true)
                .description("성공")
                .data(null)
                .build();
    }

    public static <T> ResponseFormat ok(T data){

        return ResponseFormat.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .check(true)
                .description("성공")
                .data(data)
                .build();
    }

    public static ResponseFormat fail(String message){

        return ResponseFormat.builder()
                .code(ResponseCode.FAIL.getCode())
                .check(false)
                .description(message)
                .data(null)
                .build();
    }

    public static ResponseFormat expire(){

        return ResponseFormat.builder()
                .code(ResponseCode.TOKEN_EXPIRED.getCode())
                .check(false)
                .description("토큰 만료")
                .data(null)
                .build();
    }
}
