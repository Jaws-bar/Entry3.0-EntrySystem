package com.entry.entrydsm.common.config;

import com.entry.entrydsm.common.exception.AlreadySubmittedException;
import com.entry.entrydsm.common.exception.UnauthorizedException;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.service.RealAuthService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

public class AuthRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private RealAuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        AuthRequired authRequired = ObjectUtils.firstNonNull(
                handlerMethod.getMethodAnnotation(AuthRequired.class),
                getClassLevelAuthRequired(handlerMethod));

        if (authRequired == null) {
            return true;
        }

        if (anyParameterTypeIsUser(handlerMethod)) {
            return true;
        }

        if (authService.validateToken(request.getHeader("Authorization")).isPresent()) {
            return true;
        }

        authService.validateToken(request.getHeader("Authorization"))
                .filter(user -> {
                    if (!authRequired.allowSubmitted() && user.isSubmitted()) {
                        throw new AlreadySubmittedException();
                    }
                    return true;
                })
                .orElseThrow(UnauthorizedException::new);

        return true;
    }

    protected boolean anyParameterTypeIsUser(HandlerMethod handlerMethod) {
        return Stream.of(handlerMethod.getMethodParameters()).anyMatch(param -> param.getParameterType().isAssignableFrom(User.class));
    }

    protected AuthRequired getClassLevelAuthRequired(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getDeclaringClass().getAnnotation(AuthRequired.class);
    }
}
