package project.olineworkout.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.olineworkout.domain.entity.user.Gender;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.service.user.UserService;

import java.time.LocalDateTime;
import java.time.Month;

@RequestMapping("/user")
@RestController
public class UserController {

//    // 성별을 입력받아서 회원가입을 하는 컨트롤러
//    @PostMapping//FEMALE
//    public void create(@RequestBody gender) {
//
//        LocalDateTime localDateTime = LocalDateTime.of(2012, Month.APRIL, 3, 0, 0, 0);
//
//        Gender sex = Gender.of(gender);
//                     UserRole.of(role);
//
//        User.builder()
//                .gender()
//                .build();
//
}
