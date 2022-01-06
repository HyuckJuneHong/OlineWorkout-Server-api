package project.olineworkout.domain.entity.Reply;

import lombok.*;
import project.olineworkout.domain.entity.board.Board;
import project.olineworkout.domain.entity.user.User;

import javax.persistence.*;

@Entity
@Table(name="tbl_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String content;

    @ManyToOne
    @JoinColumn(name = "uno")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bno") //외래키를 매핑할 때 사용.
    private Board board;

    @Builder
    public Reply(String content, User user, Board board) {
        this.content = content;
        this.user = user;
        this.board = board;
    }

    public void updateContent(String content){
        this.content = content;
    }
}
