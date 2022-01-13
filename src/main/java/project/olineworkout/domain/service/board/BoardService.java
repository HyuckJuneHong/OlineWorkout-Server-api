package project.olineworkout.domain.service.board;

import project.olineworkout.domain.dto.BoardDto;
import project.olineworkout.domain.shared.ResponseFormat;

public interface BoardService {

//    게시판 작성 서비스
    ResponseFormat writeBoard(BoardDto.CREATE create);

//    게시판 수정 서비스
    ResponseFormat modifyBoard(BoardDto.UPDATE update);

//    게시판 조회 서비스
    ResponseFormat readBoard(BoardDto.READ read);

}
