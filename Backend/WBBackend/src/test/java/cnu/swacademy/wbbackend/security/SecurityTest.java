package cnu.swacademy.wbbackend.security;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("권한 없는 사용자가 접근할 경우 login Page 로 리다이렉트")
    @WithAnonymousUser
    void anonymousTest() throws Exception {
        mockMvc.perform(get("/api/authenticated"))
                .andDo(print()).andExpect(redirectedUrl("http://localhost/login"));
        System.out.println("인증 객체 : " + SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    @DisplayName("권한 있는 사용자가 접근할 경우 OK. mock 이라 404 Not Found 발생")
    @WithMockUser
    void authenticatedUserTest() throws Exception {
        System.out.println("인증 객체 : " + SecurityContextHolder.getContext().getAuthentication());
        mockMvc.perform(get("/api/authenticated")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Form Login 테스트")
    void formLoginTest() throws Exception {
        String username = "newUser";
        String password = "pass";
        Member enroll = enroll(username, password);

        mockMvc.perform(formLogin().user(username).password(password))
                .andExpect(authenticated().withUsername(username).withRoles("USER"));
    }

    Member enroll(String username, String password) {
        Member member = new Member(username, password, "nick", "ROLE_USER");
        Member save = memberRepository.save(member);
        return save;
    }
}