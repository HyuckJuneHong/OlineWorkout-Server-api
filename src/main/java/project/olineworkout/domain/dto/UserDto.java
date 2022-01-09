package project.olineworkout.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import project.olineworkout.domain.entity.user.Gender;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*
    static 내부 클래스는 자신의 바깥 클래스 인스턴스의 임시적 참조를 유지하지 않는다.
    즉 static 내부 클래스로 선언하면 메모리 누수의 일반적인 원인을 예방할 수 있고, 클래스의 각 인스턴스당 더 적은 메모리를 사용하게 된다.
    1. static이 아닌 멤버 클래스의 인스턴스는 바깥 클래스의 인스턴스와 암묵적으로 연결된다.
    2. 왜냐하면 static이 아닌 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없기 때문이다.
    3. 두 클래스의 관계는 멤버 클래스의 인스턴스 안에 만들어지며, 메모리를 차지한다. 생성도 느리다.


 */
 public class UserDto {


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CREATE{

        @ApiModelProperty(example = "사용할 아이디") //example - 지정된 임의 테스트 값을 입력 함
        @NotBlank(message = "사용할 아이디를 입력해주세요.")
        @Size(min = 5, max = 16, message = "아이디는 최소 5자, 최대 16자 입력해야 합니다.")
        private String identity;

        @ApiModelProperty(example = "사용할 비밀번호")
        @NotBlank(message = "사용할 비밀번호를 입력해주세요.")
        private String password;

        @ApiModelProperty(example = "ROLE_USER")
        private String userCode;

        @ApiModelProperty(example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @ApiModelProperty(example = "MALE or FEMALE")
        @NotBlank(message = "성별을 입력하세요.")
        private Gender gender;

        @ApiModelProperty(example = "19980122")
        @NotBlank(message = "생년월일을 입력해주세요.")
        private String birthDay;

        @ApiModelProperty(example = "010-xxxx-xxxx")
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;

    }
}
