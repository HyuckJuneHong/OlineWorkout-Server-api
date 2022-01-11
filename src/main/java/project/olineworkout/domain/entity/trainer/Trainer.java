package project.olineworkout.domain.entity.trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_trainer")
@NoArgsConstructor
//@AllArgsConstructor
@Getter
public class Trainer extends BaseEntity {

    private String company;     //소속
    private String license;     //자격증
    private String career;      //경력
    private String annual;      //연차
    private String major;       //전공

    @OneToOne
    @JoinColumn(name = "uno")
    private User user;

    @Builder
    public Trainer(String company, String license, String career, String annual, String major, User user) {

        this.company = company;
        this.license = license;
        this.career = career;
        this.annual = annual;
        this.major = major;
        this.user = user;

    }

    /*TODO
    실시간으로 날짜끌어와서, 작성한 년도와 비교하여 연도가 바뀔때마다 연차 +1 되는 함수
     */

    public void updateTrainer(String company, String license, String career){
        this.company = company;
        this.license = license;
        this.career = career;
    }
}
