package project.olineworkout.controller.member;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.olineworkout.domain.dto.MemberDto;
import project.olineworkout.domain.service.member.MemberService;
import project.olineworkout.domain.shared.ResponseFormat;

@RequestMapping("/onlineworkout")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<String> login(@RequestBody MemberDto.LOGIN dto) {
        return ResponseFormat.ok(memberService.login(dto));
    }


    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public ResponseFormat<Void> signUp(@RequestBody MemberDto.CREATE dto) {

        memberService.signUp(dto);
        return ResponseFormat.ok();
    }
}
