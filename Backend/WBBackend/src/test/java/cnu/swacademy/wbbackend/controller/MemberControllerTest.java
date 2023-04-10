package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.model.RegisterMemberForm;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import cnu.swacademy.wbbackend.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testRegister() throws Exception {

        RegisterMemberForm form = new RegisterMemberForm();
        form.setUsername("testuser");
        form.setPassword("testpassword");
        form.setNickname("testnickname");

        // form 데이터를 JSON 형식으로 변환하여 요청 본문에 추가
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(form);

        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("회원가입 성공"));
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
        mockMvc.perform(post("/api/members")
                        .params(params))
                .andExpect(status().is4xxClientError());
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
        mockMvc.perform(get("/api/members/"+ memberId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("mockUser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("nickname1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.authority").value("ROLE_USER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").doesNotExist());
    }
}