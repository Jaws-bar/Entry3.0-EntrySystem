package com.entry.entrydsm.common.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.method.HandlerMethod;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AuthRequiredInterceptorTest {

    // TODO: Add test cases
    @Test
    public void name() {
        assertThat((HandlerMethod) null).isNull();
    }
}