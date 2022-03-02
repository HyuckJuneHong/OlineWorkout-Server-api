package project.olineworkout.domain.entity.reply;
import lombok.*;
import project.olineworkout.domain.entity.board.Board;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="tbl_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reply extends BaseEntity {

    private String content;

    @ManyToOne
    @JoinColumn(name = "uno")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bno") //외래키를 매핑할 때 사용.
    private Board board;

    @Builder
    public Reply(String content, Member member, Board board) {
        this.content = content;
        this.member = member;
        this.board = board;
    }

    /**
     * 댓글 수정
     * @param content
     */
//    public void updateContent(String content){
//        this.content = content;
//    }
}