package project.olineworkout.domain.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import project.olineworkout.domain.dto.MemberDto;
import project.olineworkout.domain.shared.Address;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member extends BaseEntity {

    @Column(unique = true, name = "identity", nullable = false, length = 100)
    private String identity;

    @JsonIgnore //서버에서 Json 응답을 생성할때 해당 필드는 ignore
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address; //state, city, dong

    // ROLE_USER , ROLE_TRAINER , ROLE_MEMBER, ROLE_ADMIN , ROLE_MANAGER
    @Enumerated(EnumType.STRING) //enum 이름을 DB에 저장
    private MemberRole memberRole;

    //MALE, FEMALE
    @Enumerated(EnumType.STRING) //enum 이름을 DB에 저장
    private Gender gender;

    private String birthDay;
    private String phone;

    // 선택입력사항 개인정보
    private Integer weight;
    private Integer height;

    @Builder
    public Member(MemberRole memberRole, String identity, String password, String name
            , Gender gender, String birthDay, String phone) {

        this.identity = identity;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phone = phone;
        this.memberRole = memberRole;
    }

    public void updateUser(MemberDto.UPDATE update) {
        this.name = update.getName();
        this.phone = update.getPhone();
        this.address = new Address("", "", "");
        this.weight = 0;
        this.height = 0;

        if(update.getState() != null && update.getCity() != null && update.getDong() != null) {
            this.address = new Address(update.getState(), update.getCity(), update.getDong());
        }if(update.getWeight() != null){
            this.weight = update.getWeight();
        }if(update.getHeight() != null){
            this.height = update.getHeight();
        }
    }

    public void updatePassword(String password){
        this.password = password;
    }

}