package cnu.swacademy.wbbackend.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @BeforeEach
    void clear() {
        memberRepository.clear();
    }

    @Test
    void save() {
        //given
        Member member = new Member("abc", "def", "nick", "ROLE_USER");

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember.getUsername()).isEqualTo("abc");
        assertThat(savedMember.getPassword()).isEqualTo("def");
        assertThat(savedMember.getNickname()).isEqualTo("nick");
        assertThat(savedMember.getAuthority()).isEqualTo("ROLE_USER");
    }

    @Test
    void findByUsername() {
        //given
        Member member = new Member("abc", "def", "nick", "ROLE_USER");
        memberRepository.save(member);

        //when
        Optional<Member> findMemberByUsername = memberRepository.findByUsername("abc");
        Member savedMember = findMemberByUsername.get();

        //then
        assertThat(findMemberByUsername).isNotEmpty();
        assertThat(savedMember.getUsername()).isEqualTo("abc");
        assertThat(savedMember.getPassword()).isEqualTo("def");
        assertThat(savedMember.getNickname()).isEqualTo("nick");
        assertThat(savedMember.getAuthority()).isEqualTo("ROLE_USER");

    }
}