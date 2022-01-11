package project.olineworkout.domain.entity.board;

import lombok.*;
import project.olineworkout.domain.dto.BoardDto;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="tbl_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseEntity {

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardType category;

    private Long likeCount;
    private Long viewCount;
    private Long replyCount;

    @ManyToOne
    @JoinColumn(name = "uno")
    private User user;

    @Builder
    public Board(String title, String content, BoardType category, Long likeCount,
                 Long viewCount, Long replyCount, User user) {

        this.title = title;
        this.content = content;
        this.category = category;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
        this.user = user;

    }

    public void update(BoardDto.UPDATE update) {
        this.title = update.getTitle();
        this.content = update.getContent();
        this.category = update.getCategory();
    }

    /**
     * 댓글 수, 좋아요 수, 조회 수 증감 메소드
     */
    public void viewCountUp(){
        this.viewCount += 1;
    }
    public void likeCountUp(){
        this.likeCount += 1;
    }
    public void likeCountDown(){
        if(likeCount > 0)
            this.likeCount -= 1;
    }
    public void replyCountUp(){
        this.replyCount += 1;
    }
    public void replyCountDown(){
        if(likeCount > 0)
            this.replyCount -= 1;
    }
}
