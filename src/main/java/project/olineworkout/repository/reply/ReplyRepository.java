package project.olineworkout.repository.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.olineworkout.domain.entity.reply.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {


}
