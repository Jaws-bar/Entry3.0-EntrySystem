package com.entry.entrydsm.common.config;

import com.entry.entrydsm.common.exception.UnauthorizedException;
import com.entry.entrydsm.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AuthRequiredArgumentResolverTest {

    @Test
    public void 파라미터_타입이_User가_아님() {
        assertThat(callSupportsParameter(mockArgumentResolver(true, true, false))).isFalse();
    }

    @Test
    public void 메소드에_AuthRequired가_없음() {
        assertThat(callSupportsParameter(mockArgumentResolver(false, true, true))).isTrue();
    }

    @Test
    public void 클래스에_AuthRequired가_없음() {
        assertThat(callSupportsParameter(mockArgumentResolver(true, false, true))).isTrue();
    }

    @Test
    public void 메소드와_클래스에_AuthRequired가_없음() {
        assertThat(callSupportsParameter(mockArgumentResolver(false, false, true))).isFalse();
    }

    @Test(expected = UnauthorizedException.class)
    public void 토큰이_유효하지_않음() throws Exception {
        callResolveArgument(mockArgumentResolver(false));
    }

    @Test
    public void 토큰이_유효함() throws Exception {
        assertThat(callResolveArgument(mockArgumentResolver(true))).isEqualTo(new User());
    }

    private boolean callSupportsParameter(AuthRequiredArgumentResolver argumentResolver) {
        return argumentResolver.supportsParameter(null);
    }

    private Object callResolveArgument(AuthRequiredArgumentResolver argumentResolver) throws Exception {
        return argumentResolver.resolveArgument(null, null, null, null);
    }

    private AuthRequiredArgumentResolver mockArgumentResolver(boolean isValid) {
        return new AuthRequiredArgumentResolver() {
            @Override
            protected Optional<User> validateToken(NativeWebRequest webRequest) {
                return isValid ? Optional.of(new User()) : Optional.empty();
            }
        };
    }

    private AuthRequiredArgumentResolver mockArgumentResolver(boolean isAnnotatedAtMethod, boolean isAnnotatedAtClass, boolean isUserType) {
        return new AuthRequiredArgumentResolver() {
            @Override
            protected boolean isAnnotatedAuthRequiredAtMethod(MethodParameter parameter) {
                return isAnnotatedAtMethod;
            }

            @Override
            protected boolean isAnnotatedAuthRequiredAtClass(MethodParameter parameter) {
                return isAnnotatedAtClass;
            }

            @Override
            protected boolean isUserType(MethodParameter parameter) {
                return isUserType;
            }
        };
    }
}