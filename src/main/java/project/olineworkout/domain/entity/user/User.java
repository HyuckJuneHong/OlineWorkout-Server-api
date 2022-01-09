package project.olineworkout.domain.entity.user;

import lombok.*;
import project.olineworkout.domain.shared.Address;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class User extends BaseEntity {

    @Column(unique = true, name = "identity", nullable = false, length = 100)
    private String identity;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address; //state, city, dong

    // ROLE_USER , ROLE_TRAINER , ROLE_MEMBER, ROLE_ADMIN , ROLE_MANAGER
    @Enumerated(EnumType.STRING) //enum 이름을 DB에 저장
    private UserRole userRole;

    //MALE, FEMALE
    @Enumerated(EnumType.STRING) //enum 이름을 DB에 저장
    private Gender gender;

    private String birthDay;
    private String phone;

    // 선택입력사항 개인정보
    private int weight;
    private int height;

    @Builder
    public User(UserRole userRole, String identity, String password, String name
            , Gender gender, String birthDay, String phone) {

        this.identity = identity;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phone = phone;
    }

    //아직 미완성 메소드 건들지 말아주세요!!!!
    public void update(String name, Address address, String phone, int weight, int height, Gender gender) {
        this.name = name;
//        this.address = new Address();
        this.phone = phone;
        this.weight = weight;
        this.height = height;
    }

    public void updatePassword(String password){
        this.password = password;
    }

}