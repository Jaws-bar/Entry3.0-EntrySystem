package com.entry.entrydsm.common.config;

import com.entry.entrydsm.common.exception.UnauthorizedException;
import com.entry.entrydsm.user.domain.User;
import com.entry.entrydsm.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuthRequiredArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private AuthService authService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return isUserType(parameter) &&
                (isAnnotatedAuthRequiredAtMethod(parameter) || isAnnotatedAuthRequiredAtClass(parameter));

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return validateToken(webRequest).orElseThrow(UnauthorizedException::new);
    }

    protected Optional<User> validateToken(NativeWebRequest webRequest) {
        return authService.validateToken(getAuthorizationHeaderString(webRequest));
    }

    private String getAuthorizationHeaderString(NativeWebRequest webRequest) {
        return ((HttpServletRequest) webRequest.getNativeRequest()).getHeader("Authorization");
    }

    protected boolean isAnnotatedAuthRequiredAtMethod(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(AuthRequired.class);
    }

    protected boolean isAnnotatedAuthRequiredAtClass(MethodParameter parameter) {
        return parameter.getMethod().getDeclaringClass().isAnnotationPresent(AuthRequired.class);
    }

    protected boolean isUserType(MethodParameter parameter) {
        return parameter.getParameterType().equals(User.class);
    }
}
