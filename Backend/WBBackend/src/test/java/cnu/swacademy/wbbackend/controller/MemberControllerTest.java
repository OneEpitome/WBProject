package cnu.swacademy.wbbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.model.RegisterMemberForm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;


    @DisplayName("회원 가입 테스트")
    @Test
    @WithMockUser
    void register() throws Exception {
        RegisterMemberForm registerMemberForm = new RegisterMemberForm();
        registerMemberForm.setUsername("user");
        registerMemberForm.setPassword("123");
        registerMemberForm.setNickname("nickname");

        Member member = new Member();
        member.setId(1L);
        member.setUsername(registerMemberForm.getUsername());
        member.setPassword(registerMemberForm.getPassword());
        member.setNickname(registerMemberForm.getNickname());
        member.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));


        when(memberService.save(any(Member.class))).thenReturn(member);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/members").with(csrf())
                        .content("{\"username\":\"user\",\"password\":\"123\",\"nickname\":\"nickname\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(member.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(member.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value(member.getNickname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorities[0].authority").value("ROLE_USER"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("회원 조회 테스트")
    @Test
    @WithMockUser
    void readMember() throws Exception {
        Long memberId = 1L;

        Member member = new Member();
        member.setId(memberId);
        member.setUsername("user");
        member.setPassword("123");
        member.setNickname("nickname");
        member.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        when(memberService.findById(memberId)).thenReturn(member);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/members/{memberId}", memberId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(member.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(member.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value(member.getNickname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorities[0].authority").value("ROLE_USER"))
                .andDo(MockMvcResultHandlers.print());
    }
}
