package project.olineworkout.domain.service.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.olineworkout.domain.dto.ReplyDto;
import project.olineworkout.domain.entity.reply.Reply;
import project.olineworkout.domain.shared.ResponseFormat;
import project.olineworkout.repository.reply.ReplyRepository;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public ResponseFormat writeReply(ReplyDto.CREATE create) {

        replyRepository.save(
                Reply.builder()
                        .content(create.getContent())
                        .board(create.getBoard())
                        .user(create.getUser())
                        .build()
        );

        return ResponseFormat.ok();
    }

    @Override
    public ResponseFormat modifyReply(ReplyDto.UPDATE update) {
        /**
         * TODO
         *  login한 User가 선택한 Reply의 User와 같을 시
         */

        return null;
    }

    @Override
    public ResponseFormat readReply(ReplyDto.READ read) {
        return null;
    }
}
