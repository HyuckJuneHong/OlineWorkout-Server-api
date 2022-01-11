package project.olineworkout.domain.entity.board;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BoardType {

    //질문, 공지사항, 홍보글, 자유게시판, 개인화 루틴
    QNA("질문글"),
    NOTICE("공지사항"),
    ADVERTISE("홍보글"),
    FREE_BOARD("자유게시판"),
    SELF_ROUTINE("개인루틴");

    private String category;

    // UserRole 참고해서 작성,
    public static BoardType of(String category) {
        return Arrays.stream(BoardType.values())
                .filter(r -> r.toString().equalsIgnoreCase(category))
                .findAny().orElseThrow(() -> new RuntimeException("해당 BoardType 카테고리를 찾을 수 없습니다."));
    }
}
