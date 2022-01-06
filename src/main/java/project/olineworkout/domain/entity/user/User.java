package project.olineworkout.domain.entity.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

@Entity
@Table(name="tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uno;

    // ROLE_USER , ROLE_TRAINER , ROLE_MEMBER, ROLE_ADMIN , ROLE_MANAGER
    private String userCode;

    // id / password
    private String identity;
    private String password;

    // 필수입력사항 개인정보
    private String name;
    private String sex;
    private String birthDay;
    private String phone;
    private String email;
    private String address;

    // 선택입력사항 개인정보
    private String weight;
    private String height;

    // 공통 Entity
    @CreationTimestamp
    private String createDate;
    @UpdateTimestamp
    private String updateDate;
    // deleteYN은 'Y', 'N' 으로 구별, 바로 DB에서 데이터 삭제하지않고 구별위해 만든 속성
    private String deleteYN;


    @Builder
    public User(String userCode, String identity, String password, String name, String sex, String birthDay,
                String phone, String email, String address, String weight, String height,
                String createDate, String updateDate, String deleteYN) {

        this.userCode = userCode;
        this.identity = identity;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.weight = weight;
        this.height = height;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteYN = deleteYN;
    }

    public void updateUser(String password, String phone, String email, String address, String weight, String height) {
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.weight = weight;
        this.height = height;
    }

}