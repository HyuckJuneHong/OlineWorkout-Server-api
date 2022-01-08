package project.olineworkout.domain.entity.user;

public enum UserCode {

    // 유저, 트레이너, 멤버, 매니저, 관리자
    /*
     * 기본 회원가입시 유저로
     * 유저는 트레이너로 등록 가능, 즉, 트레이너는 유저이면서 트레이너이다.
     * 유저중에 트레이너와 매칭시 멤버로
     * 간단히 페이지 관리할 수 있는 매니저
     * 모든 권한과 접근제한이 없는 관리자 (관리자는 유저를 매니저로 승격 시킬 수 있다.)
     * */
    ROLE_USER, ROLE_TRAINER, ROLE_MEMBER, ROLE_MANAGER, ROLE_ADMIN
}