package ru.otus.config;


import feign.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//            .antMatchers("/")
//            .antMatchers("/swagger/swagger-ui.html")
//            .antMatchers("/webjars/springfox-swagger-ui/**")
//            .antMatchers("/swagger-resources/**")
//            .antMatchers("/v2/api-docs")
//            .antMatchers("/h2-console/**");
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .httpBasic()
                .and()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                .authorizeRequests().antMatchers("/login-form-processing").anonymous()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE,  "/client/api/genres/**", "/client/api/authors/**", "/client/api/books/**", "/client/api/comments/**" , "/client/api/comment/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/client/api/books/**" , "/client/api/authors/**", "/client/api/genres/**" , "/client/api/comments/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/client/api/comments/**").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login-form-processing")
                .usernameParameter("username-for-login")
                .passwordParameter("password-for-login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-form")
                .and()
                //.rememberMe()
                .userDetailsService(userDetailsService)
                //.alwaysRemember(true)
        ;

    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }


}
