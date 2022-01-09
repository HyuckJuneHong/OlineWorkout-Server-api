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
        return Arrays.stream(Gender.values())
                .filter(g -> g.toString().equalsIgnoreCase(gender))
                .findAny().orElseThrow(() -> new RuntimeException("Gender 항목을 찾을 수 없습니다."));
    }
}
