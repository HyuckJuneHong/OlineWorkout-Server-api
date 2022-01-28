package project.olineworkout.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.olineworkout.domain.dto.UserDto;
import project.olineworkout.domain.service.user.UserService;
import project.olineworkout.domain.shared.ResponseFormat;

@RequestMapping("/onlineworkout/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ResponseFormat<String> login(@RequestBody UserDto.LOGIN dto) {
        return ResponseFormat.ok(userService.login(dto));
    }


    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public ResponseFormat<Void> signUp(@RequestBody UserDto.CREATE dto) {

        userService.signUp(dto);
        return ResponseFormat.ok();
    }
}
