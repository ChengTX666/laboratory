package nefu.laboratory.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.Service.UserService;
import nefu.laboratory.component.JWTComponent;
import nefu.laboratory.dox.User;
import nefu.laboratory.dto.Login;
import nefu.laboratory.dto.ResultVO;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final JWTComponent jwtComponent;
    private final CacheManager cacheManager;

    @GetMapping("cache")
    public ResultVO cacheTest(){
        Cache lab = cacheManager.getCache("lab");
        if(lab!=null){
            return ResultVO.success(Map.of("cache",lab.getNativeCache()));
        }
        return ResultVO.error(400,"无缓存");
    }


    @Operation(summary = "登录",description = "成功后返回user对象,header返回token")
    @PostMapping("login")
    public ResultVO login(@RequestBody Login login, HttpServletResponse resp) {

        User user = userService.getUser(login.getAccount());

        if (user == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            return ResultVO.error(401, "用户名或密码错误");
        }
        String token = jwtComponent.encode(Map.of("uid", user.getId(),"name",user.getName(),"role", user.getRole()));
        resp.addHeader("token", token);
        resp.addHeader("role", user.getRole());
        return ResultVO.success(Map.of("user",user));
    }
}
