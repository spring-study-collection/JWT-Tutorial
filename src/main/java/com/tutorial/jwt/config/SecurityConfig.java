package com.tutorial.jwt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity //기본적인 web 보안을 활성화하겠다는 의미
// 추가적인 설정을 위해 WebSecurityConfigure를 implements 하거나
// WebSecurityConfigureAdapter를 extends 하는 방법이 있다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //h2-console 하위 모든 요청과 파비콘 관련 요청은 Spring Security 로직을 수행하지 않도록 설정
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //HttpServletRequest를 사용하는 요청들에 대한 접근 제한을 설정하겠다는 의미
                .antMatchers("/api/hello").permitAll() //"/api/hello"에 대한 요청은 인증없이 접근을 허용하겠다는 의미
                .anyRequest().authenticated(); //나머지 요청들은 모두 인증되어야 한다는 의미
    }
}
