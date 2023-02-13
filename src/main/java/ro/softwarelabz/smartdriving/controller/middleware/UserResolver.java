package ro.softwarelabz.smartdriving.controller.middleware;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import ro.softwarelabz.smartdriving.user.domain.User;
import ro.softwarelabz.smartdriving.user.service.UserService;


@Service
@AllArgsConstructor
public class UserResolver {
    private final UserService userService;

    public User getUser() {
        Authentication springAuth = SecurityContextHolder.getContext().getAuthentication();

        if ((springAuth instanceof JwtAuthenticationToken)) {
            JwtAuthenticationToken jwt = (JwtAuthenticationToken) springAuth;
            var claims = jwt.getToken().getClaims();

            User user = User.from(claims);
            return userService.getUserBySub(user.getSub())
                    .orElseGet(() -> userService.create(user));
        }

        return null;
    }
}
