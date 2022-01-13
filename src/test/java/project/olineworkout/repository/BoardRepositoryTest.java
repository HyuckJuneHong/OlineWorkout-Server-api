package project.olineworkout.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.olineworkout.domain.entity.board.Board;
import project.olineworkout.domain.entity.board.BoardType;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.repository.board.BoardRepository;
import project.olineworkout.repository.user.UserRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 게시판_데이터_생성_테스트() {

        IntStream.rangeClosed(1, 10).forEach(i -> {

            Optional<User> result = userRepository.findById(10L);
            if (result.isPresent()) {
                User user = result.get();

                Board board = Board.builder()
                        .title("Title_" + i)
                        .content("Content_" + i)
                        .category(BoardType.FREE_BOARD)
                        .user(user)
                        .build();

                boardRepository.save(board);
            }
        });
    }
}
