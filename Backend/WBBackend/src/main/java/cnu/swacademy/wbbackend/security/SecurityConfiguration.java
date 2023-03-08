package cnu.swacademy.wbbackend.security;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(memberService)
                .formLogin()
//                .loginPage("/api/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .and()
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers("/api/authenticated").authenticated()
                                .anyRequest().permitAll()
                )

                // H2-Console 을 이용하기 위한 csrf, frameOption Disable 처리
                .csrf().disable()
                .headers().frameOptions().disable();
    return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // MockMember for Dev
    @PostConstruct
    void setup() {
        memberService.save(
                new Member("user", "123", "nickname", "ROLE_USER")
        );
    }
}
