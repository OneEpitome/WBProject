package cnu.swacademy.wbbackend.service;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.exception.MemberNotFoundException;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("test", "test1234", "tester");
        when(memberRepository.findByUsername(member.getUsername())).thenReturn(Optional.of(member));
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));
        when(passwordEncoder.encode(member.getPassword())).thenReturn("encodedTest1234");
    }

    @Test
    @DisplayName("사용자 이름으로 Member 조회")
    void loadUserByUsername() {
        assertEquals(member, memberService.loadUserByUsername(member.getUsername()));
    }

    @Test
    @DisplayName("존재하는 Member ID로 조회")
    void findById() {
        assertEquals(member, memberService.findById(member.getId()));
    }

    @Test
    @DisplayName("존재하지 않는 Member ID로 조회")
    void findById_NotFound() {
        assertThrows(MemberNotFoundException.class, () -> memberService.findById(-1L));
    }

    @Test
    @DisplayName("새로운 Member 추가")
    void save() {
        memberService.save(member);
        verify(memberRepository, times(1)).save(any(Member.class));
    }
}
