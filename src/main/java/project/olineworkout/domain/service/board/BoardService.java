package project.olineworkout.domain.service.board;

import project.olineworkout.domain.dto.BoardDto;
import project.olineworkout.domain.shared.ResponseFormat;

public interface BoardService {

    ResponseFormat register(BoardDto.CREATE create);

    ResponseFormat modify(BoardDto.UPDATE update);

}
