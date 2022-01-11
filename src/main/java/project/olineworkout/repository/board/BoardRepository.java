package project.olineworkout.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.olineworkout.domain.entity.board.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


}
