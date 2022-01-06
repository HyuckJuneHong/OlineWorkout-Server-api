package project.olineworkout.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.Trainer.Trainer;
import project.olineworkout.domain.entity.User.User;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @OneToOne
    @JoinColumn(name = "uno")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tno")
    private Trainer trainer;

    @Builder
    public Member(User user, Trainer trainer) {
        this.user = user;
        this.trainer = trainer;
    }

    /*TODO
    Member와 Mathching 차이점?
     */
}
