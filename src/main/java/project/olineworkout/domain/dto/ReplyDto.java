package project.olineworkout.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.board.Board;
import project.olineworkout.domain.entity.user.User;

import javax.validation.constraints.NotBlank;

public class ReplyDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CREATE {

        @ApiModelProperty(example = "댓글 내용")
        @NotBlank(message = "댓글 내용을 입력하세요.")
        private String content;

        private User user;

        private Board board;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UPDATE {

        @ApiModelProperty(example = "댓글 수정")
        @NotBlank(message = "댓글 내용을 입력하세요.")
        private String content;

        private User user;

        private Board board;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class READ {

        @ApiModelProperty(example = "댓글 내용")
        private String content;

        @ApiModelProperty(example = "작성자 아이디")
        private String userIdentity;

        @ApiModelProperty(example = "작성된 게시판 제목")
        private String boardTitle;
    }

}
