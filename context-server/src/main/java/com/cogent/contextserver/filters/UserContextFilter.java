package com.cogent.contextserver.filters;

import com.cogent.genericserver.config.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class UserContextFilter implements Filter {

    private final JwtConfig jwtConfig;

    public UserContextFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {

        log.info(":: :::: ::::: :::: USER CONTEXT FILTER TRIGGERED :: ::: ::::: :::: ");

        log.info("HEADER :: {}", jwtConfig.getHeader());
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("I am entering the licensing service id with auth token ****: " +
                httpServletRequest.getHeader("username"));
        UserContextHolder.getContext().setUsername(httpServletRequest.getHeader("username"));

        chain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}