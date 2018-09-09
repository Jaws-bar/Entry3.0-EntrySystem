package com.entry.entrydsm.common.config;

import com.entry.entrydsm.common.exception.UnauthorizedException;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

public class AuthRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (!handlerMethod.hasMethodAnnotation(AuthRequired.class)
                && !isAnnotatedAuthRequiredAtClass(handlerMethod)) {
            return true;
        }

        if (anyParameterTypeIsUser(handlerMethod)) {
            return true;
        }

        if (authService.validateToken(request.getHeader("Authorization")).isPresent()) {
            return true;
        }

        throw new UnauthorizedException();
    }

    protected boolean anyParameterTypeIsUser(HandlerMethod handlerMethod) {
        return Stream.of(handlerMethod.getMethodParameters()).anyMatch(param -> param.getParameterType().isAssignableFrom(User.class));
    }

    protected boolean isAnnotatedAuthRequiredAtClass(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getDeclaringClass().isAnnotationPresent(AuthRequired.class);
    }
}
