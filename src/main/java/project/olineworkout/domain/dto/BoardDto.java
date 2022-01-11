package project.olineworkout.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.olineworkout.domain.entity.board.BoardType;

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

    }
}
