package project.olineworkout.domain.entity.board;

import com.sun.istack.NotNull;
import lombok.*;
import project.olineworkout.domain.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tbl_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @NotNull //nullable = false 와는 달리 유효성 검사까지 해줌
    private String title;

    private String content;
    private String theme;   //게시판 테마

    private LocalDate createDate;
    private LocalDate updateDate;

    private Long likeCount;
    private Long viewCount;
    private Long replyCount;

    @ManyToOne
    @JoinColumn(name = "uno")
    private User user;

    @Builder
    public Board(String title, String content, String theme, LocalDate createDate, LocalDate updateDate
            , Long likeCount, Long viewCount, Long replyCount, User user) {

        this.title = title;
        this.content = content;
        this.theme = theme;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.replyCount = replyCount;
        this.user = user;
    }

    /**
     * 게시판 수정 메소드
     * @param title
     * @param content
     */
    public void updateBoard(String title, String content){

        this.title = title;
        this.content = content;
        this.updateDate = LocalDate.now();
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
