package com.entry.entrydsm.common.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

public class AuthRequiredInterceptor implements HandlerInterceptor {

//    @Autowired
//    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (anyParameterHasAuthRequired(handlerMethod)) {
            return true;
        }

        if (!handlerMethod.hasMethodAnnotation(AuthRequired.class)
                && !isAnnotatedAuthRequiredAtClass(handlerMethod)) {
            return true;
        }

//        if (userService.findById().isPresent()) {
//            return true;
//        }
//
//        throw new UnauthorizedException();
        return true;
    }

    protected boolean anyParameterHasAuthRequired(HandlerMethod handlerMethod) {
        return Stream.of(handlerMethod.getMethodParameters()).anyMatch(param -> param.hasParameterAnnotation(AuthRequired.class));
    }

    protected boolean isAnnotatedAuthRequiredAtClass(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getDeclaringClass().isAnnotationPresent(AuthRequired.class);
    }
}
