package ro.softwarelabz.smartdriving.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.softwarelabz.smartdriving.controller.middleware.UserMethodArgumentResolver;
import ro.softwarelabz.smartdriving.controller.middleware.UserResolver;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final UserResolver userResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserMethodArgumentResolver(userResolver));
    }
}
