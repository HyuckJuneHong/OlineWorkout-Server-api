package project.olineworkout.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.board.BoardType;
import project.olineworkout.domain.entity.user.User;

import javax.validation.constraints.NotBlank;

public class BoardDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CREATE {

        @ApiModelProperty(example = "게시판 제목")
        @NotBlank(message = "제목을 입력하세요.")
        private String title;

        @ApiModelProperty(example = "게시판 내용")
        @NotBlank(message = "내용을 입력하세요.")
        private String content;

        @ApiModelProperty(example = "QNA, NOTICE, ADVERTISE, FREE_BOARD, SELF_ROUTINE")
        @NotBlank(message = "카테고리를 입력하세요.")
        private BoardType category;

        private User user;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UPDATE{

        @ApiModelProperty(example = "게시판 제목")
        @NotBlank(message = "제목을 입력하세요.")
        private String title;

        @ApiModelProperty(example = "게시판 내용")
        @NotBlank(message = "내용을 입력하세요.")
        private String content;

        @ApiModelProperty(example = "QNA, NOTICE, ADVERTISE, FREE_BOARD, SELF_ROUTINE")
        @NotBlank(message = "카테고리를 입력하세요.")
        private BoardType category;

        private User user;
    }

    @Getter
    public static class READ {

        private Long id;

        @ApiModelProperty(example = "게시판 제목")
        private String title;

        @ApiModelProperty(example = "게시판 내용")
        private String content;

        @ApiModelProperty(example = "게시판 카테고리")
        private BoardType category;

        @ApiModelProperty(example = "작성자 아이디")
        private String userIdentity;

    }
}
