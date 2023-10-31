package ru.otus.config;

import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


//@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter */{

//    private final UserDetailsService userDetailsService;

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/")
//                .antMatchers("/swagger/swagger-ui.html")
//                .antMatchers("/webjars/springfox-swagger-ui/**")
//                .antMatchers("/hystrix/**")
//                .antMatchers("/eureka/**")
//                .antMatchers("/h2-console/**");
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .cors().disable()
//                .httpBasic()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests().antMatchers("/login-form-processing").anonymous()
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/comments/**").hasAnyRole("USER", "ADMIN")
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/genres/**", "/api/authors/**").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.DELETE,  "/api/books/**", "/api/comments/**", "/api/comment/**").hasAnyRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/books/**", "/api/authors/**", "/api/genres/**").hasRole("ADMIN")
//                .and()
//                .authorizeRequests().antMatchers("/**").authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login-form-processing")
//                .usernameParameter("username-for-login")
//                .passwordParameter("password-for-login")
//                .defaultSuccessUrl("/", true)
//                .failureUrl("/login-form")
//                .and()
//                .rememberMe()
//                .userDetailsService(userDetailsService)
//                .alwaysRemember(true)
//        ;
//
//    }
//
//
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder;
//    }


}
