package nefu.laboratory.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.UserService;
import nefu.laboratory.component.JWTComponent;
import nefu.laboratory.dto.Login;
import nefu.laboratory.dto.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/laboratory/")
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final JWTComponent jwtComponent;


    @PostMapping("login")
    public ResultVO login(@RequestBody Login login, HttpServletResponse resp) {

//        User user = userService.getUser(login.getAccount());
//
//        if (user == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) {
//            return ResultVO.error(401, "用户名或密码错误");
//        }
//        String token = jwtComponent.encode(Map.of("uid", user.getId(), "role", user.getRole()));
//        resp.addHeader("token", token);
//        resp.addHeader("role", user.getRole());//测试使用
//        return ResultVO.success(Map.of("user",user));


        //测试使用
        String token=jwtComponent.encode(Map.of("uid","1","role","teacher"));
        resp.addHeader("token", token);
        resp.addHeader("role", "teacher");//测试使用
        return ResultVO.success(Map.of("user","user"));
    }
}
