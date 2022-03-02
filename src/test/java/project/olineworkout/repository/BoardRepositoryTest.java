package project.olineworkout.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.olineworkout.domain.entity.board.Board;
import project.olineworkout.domain.entity.board.BoardType;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.repository.board.BoardRepository;
import project.olineworkout.repository.member.MemberRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 게시판_데이터_생성_테스트() {

        IntStream.rangeClosed(1, 10).forEach(i -> {

            Optional<Member> result = memberRepository.findById(10L);
            if (result.isPresent()) {
                Member member = result.get();

                Board board = Board.builder()
                        .title("Title_" + i)
                        .content("Content_" + i)
                        .category(BoardType.FREE_BOARD)
                        .user(member)
                        .build();

                boardRepository.save(board);
            }
        });
    }
}
