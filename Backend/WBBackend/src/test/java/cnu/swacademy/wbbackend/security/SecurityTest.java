package cnu.swacademy.wbbackend.security;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import cnu.swacademy.wbbackend.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("권한 없는 사용자가 접근할 경우 login Page 로 리다이렉트")
    @WithAnonymousUser
    void anonymousTest() throws Exception {
        mockMvc.perform(get("/api/authenticated"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @DisplayName("권한 있는 사용자가 접근할 경우 OK. mock 이라 404 Not Found 발생")
    @WithMockUser
    void authenticatedUserTest() throws Exception {
        mockMvc.perform(get("/api/authenticated")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Form Login 테스트")
    void formLoginTest() throws Exception {
        String username = "newUser";
        String password = "pass";
        enroll(username, password);

        mockMvc.perform(formLogin().user(username).password(password))
                .andExpect(authenticated().withUsername(username).withRoles("USER"));
    }

    Member enroll(String username, String password) {
        Member member = new Member(username, password, "nick", "ROLE_USER");
        Member save = memberService.save(member);
        return save;
    }
}