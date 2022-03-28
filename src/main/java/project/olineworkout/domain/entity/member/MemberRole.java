package project.olineworkout.domain.entity.member;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MemberRole {

    /**
     * 기본 회원가입시 유저로
     * 유저는 트레이너로 등록 가능, 즉, 트레이너는 유저이면서 트레이너이다.
     * 유저중에 트레이너와 매칭시 멤버로
     * 간단히 페이지 관리할 수 있는 매니저
     * 모든 권한과 접근제한이 없는 관리자 (관리자는 유저를 매니저로 승격 시킬 수 있다.)
     */
    // 유저, 트레이너, 멤버, 매니저, 관리자
    ROLE_MEMBER("멤버"),
    ROLE_TRAINER("트레이너"),
    ROLE_MANAGER("매니저"),
    ROLE_ADMIN("관리자");

    private String role;

    //equalsIgnoreCase 대소문자 관계 없이 체크
    public static MemberRole of(String role) {
        return Arrays.stream(MemberRole.values())
                .filter(r -> r.toString().equalsIgnoreCase(role))
                .findAny().orElseThrow(() -> new RuntimeException("해당 Role 권한을 찾을 수 없습니다."));
    }
}