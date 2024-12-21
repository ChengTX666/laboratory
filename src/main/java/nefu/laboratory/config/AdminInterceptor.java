package nefu.laboratory.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nefu.laboratory.component.JWTComponent;
import nefu.laboratory.dox.User;
import nefu.laboratory.dto.Code;
import nefu.laboratory.dto.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@RequiredArgsConstructor
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    private final JWTComponent jwtComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        DecodedJWT decodeJWT = jwtComponent.decode(token);

        String uid=decodeJWT.getClaim("uid").asString();
        String role = decodeJWT.getClaim("role").asString();
        if(!User.ROLE_ADMIN.equals(role)) {
            throw XException.builder()
                    .code(Code.FORBIDDEN)
                    .build();
        }
        request.setAttribute("uid",uid);
        request.setAttribute("role",role);
        return true;
    }

}
