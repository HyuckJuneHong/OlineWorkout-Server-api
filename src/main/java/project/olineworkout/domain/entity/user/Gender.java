package project.olineworkout.domain.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {

    /**
     * MALE = 남성
     * FEMALE = 여성
     */
    MALE("남성"),
    FEMALE("여성");

    String gender;

    /**
     * 웹에서 Gender 값을 잘못 넣었을 때 알기 위해.
     * @param gender
     * @return
     */
    public static Gender of(String gender) {

        //Arrays.stream(): 배열 스트림 생성
        //values(): (static 메소드) 열거된 모든 원소를 배열에 담아 순서대로 리턴
        //equalsIgnoreCase(): 대소문자를 구분하지 않고 문자열 비교.
        //findAny: 병렬 처리에서 Multi thread에서 Stream을 처리할 때 가장 먼저 찾은 요소를 리턴
        //orElseThrow(): 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴. (500에러가 안뜨기 위해)
        return Arrays.stream(Gender.values())
                .filter(g -> g.toString().equalsIgnoreCase(gender))
                .findAny().orElseThrow(() -> new RuntimeException("Gender 항목을 찾을 수 없습니다."));
    }
}
