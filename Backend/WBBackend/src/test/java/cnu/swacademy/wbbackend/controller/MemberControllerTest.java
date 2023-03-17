package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("Member 생성 후 저장 테스트 (정상 경로)")
    void createMemberTest() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "mockUser");
        params.add("password", "123");
        params.add("nickname", "nickkname");

        //when
        mockMvc.perform(post("/api/member/create").params(params));
        //then
        assertThat(memberRepository.findMemberByUsername("mockUser")).isNotEmpty();
    }

    @Test
    @DisplayName("Member 생성 후 저장 폼 검증 테스트")
    void createMemberFormValidationTest() throws Exception {
        //given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "mockUser");
        params.add("password", "123");

        //when
        //then
        mockMvc.perform(post("/api/member/create")
                        .params(params))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @DisplayName("Member 를 memberId 로 조회하여 정보를 가져올 수 있다.")
    void readMember() throws Exception {
        //given

        Member member = new Member("mockUser", "password", "nickname1", "ROLE_USER");
        memberRepository.save(member);

        //when
        //then
        Long memberId = memberRepository.findAll().get(0).getId();
        mockMvc.perform(get("/api/member/"+ memberId))
                .andExpect(MockMvcResultMatchers.jsonPath("username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("nickname").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("reviewList").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("authority").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("password").doesNotExist());
    }
}