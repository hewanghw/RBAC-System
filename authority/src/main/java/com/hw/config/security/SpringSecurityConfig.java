package com.hw.config.security;

import com.hw.config.security.filter.CheckTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomerUserDetailsService customerUserDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    private final CustomerAccessDeniedHandler customerAccessDeniedHandler;
    private final CheckTokenFilter checkTokenFilter;

    public SpringSecurityConfig(CustomerUserDetailsService customerUserDetailsService, LoginSuccessHandler loginSuccessHandler, LoginFailureHandler loginFailureHandler, AnonymousAuthenticationHandler anonymousAuthenticationHandler, CustomerAccessDeniedHandler customerAccessDeniedHandler, CheckTokenFilter checkTokenFilter) {
        this.customerUserDetailsService = customerUserDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
        this.loginFailureHandler = loginFailureHandler;
        this.anonymousAuthenticationHandler = anonymousAuthenticationHandler;
        this.customerAccessDeniedHandler = customerAccessDeniedHandler;
        this.checkTokenFilter = checkTokenFilter;
    }

    //注入加密处理类
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(checkTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
        //表单登录
        http.formLogin()
                .loginProcessingUrl("/user/login")
                // 设置登录验证成功或失败后的的跳转地址
                .successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
                // 禁用csrf防御机制
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)
                .accessDeniedHandler(customerAccessDeniedHandler)
                .and().cors(); //开启跨域配置
    }

    //配置认证处理器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
    }

}
