package project.olineworkout.domain.entity.User;

public enum UserCode {

    // 유저, 트레이너, 멤버, 매니저, 관리자
    /*
    * 기본 회원가입시 유저로
    * 트레이너 등록시 트레이너로
    * 유저중에 트레이너와 매칭시 멤버로
    * 간단히 페이지 관리할 수 있는 매니저
    * 모든 권한과 접근제한이 없는 관리자
    * */
    ROLE_USER, ROLE_TRAINER, ROLE_MEMBER, ROLE_MANAGER, ROLE_ADMIN
}
