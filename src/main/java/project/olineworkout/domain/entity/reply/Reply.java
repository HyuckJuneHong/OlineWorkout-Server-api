package project.olineworkout.domain.entity.reply;
import lombok.*;
import project.olineworkout.domain.entity.board.Board;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="tbl_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Reply extends BaseEntity {

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

    /**
     * 댓글 수정
     * @param content
     */
    public void updateContent(String content){
        this.content = content;
    }
}