package project.olineworkout.domain.entity.matching;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.trainer.Trainer;
import project.olineworkout.domain.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_matching")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String price;
    private String purpose;
    //    private String term;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToOne
    @JoinColumn(name = "uno")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tno")
    private Trainer trainer;

    @Builder
    public Matching(String price, String purpose, LocalDateTime startDate, LocalDateTime endDate,
                    User user, Trainer trainer) {

        this.price = price;
        this.purpose = purpose;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.trainer = trainer;

    }

    /*TODO
    endDate - stratDate 하여 남은날짜 계산 함수
    +
    endDate가 넘어가면 ,,, 어떻게 해야되는거지?
     */
}
