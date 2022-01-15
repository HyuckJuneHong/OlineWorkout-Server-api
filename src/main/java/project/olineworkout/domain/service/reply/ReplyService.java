package project.olineworkout.domain.service.reply;

import project.olineworkout.domain.dto.ReplyDto;
import project.olineworkout.domain.shared.ResponseFormat;

public interface ReplyService {

    ResponseFormat writeReply(ReplyDto.CREATE create);

    ResponseFormat modifyReply(ReplyDto.UPDATE update);

    ResponseFormat readReply(ReplyDto.READ read);

}
