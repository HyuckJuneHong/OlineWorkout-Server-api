package project.olineworkout.domain.entity.matching;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.trainer.Trainer;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_matching")
@NoArgsConstructor
@Getter
public class Matching extends BaseEntity {

    private String price;
    private String purpose;
    //    private String term;

    @Column(name = "startDate")
    private LocalDateTime startDate;
    @Column(name = "endDate")
    private LocalDateTime endDate;

    @OneToOne
    @JoinColumn(name = "uno")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "tno")
    private Trainer trainer;

    @Builder
    public Matching(String price, String purpose, LocalDateTime startDate, LocalDateTime endDate,
                    Member member, Trainer trainer) {

        this.price = price;
        this.purpose = purpose;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.trainer = trainer;

    }

    /*TODO
    endDate - stratDate 하여 남은날짜 계산 함수
    +
    endDate가 넘어가면 ,,, 어떻게 해야되는거지?
     */
}
