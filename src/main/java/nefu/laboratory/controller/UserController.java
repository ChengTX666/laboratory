package nefu.laboratory.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nefu.laboratory.Service.UserService;
import nefu.laboratory.dox.User;
import nefu.laboratory.dto.ResultVO;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
@Tag(name = "共有模块")
public class UserController {

    private final UserService userService;


    @PatchMapping("update")
    public ResultVO updateUser(User user){

        return ResultVO.success(userService.update(user));
    }
}
