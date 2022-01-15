package project.olineworkout.domain.service.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.olineworkout.domain.dto.BoardDto;
import project.olineworkout.domain.entity.board.Board;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.shared.ResponseFormat;
import project.olineworkout.repository.board.BoardRepository;
import project.olineworkout.repository.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseFormat writeBoard(BoardDto.CREATE create) {

        /**
         * TODO
         *  login 여부 확인 후 로그인 일때 작성가능하도록
         *  -> security 구현 후 if조건 필요
         */
        boardRepository.save(
                Board.builder()
                        .title(create.getTitle())
                        .content(create.getContent())
                        .category(create.getCategory())
                        .user(create.getUser())
                        .build()
        );

        return ResponseFormat.ok();

    }

    @Override
    public ResponseFormat modifyBoard(BoardDto.UPDATE update) {
        /**
         * TODO
         *  login한 User가 선택한 Board의 User와 같을 시
         */
//        Board board;
//        board.update(update);
//        boardRepository.save(board);

        return ResponseFormat.ok();
    }

    @Override
    public ResponseFormat readBoard(BoardDto.READ read) {

        Optional<Board> result = boardRepository.findById(read.getId());

        if (result.isEmpty()) {
            return ResponseFormat.fail("조회가능한 게시물이 존재하지 않습니다.");
        }

        result.get().viewCountUp();
        boardRepository.save(result.get());

        return ResponseFormat.ok();
    }

}
