package cnu.swacademy.wbbackend.domain.member;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    void save() {
        //given
        Member member = new Member("abc", "def", "nick", "ROLE_USER");

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(savedMember.getUsername()).isEqualTo("abc");
        assertThat(savedMember.getPassword()).isEqualTo(member.getPassword());
        assertThat(savedMember.getNickname()).isEqualTo("nick");
        assertThat(savedMember.getAuthority()).isEqualTo("ROLE_USER");
    }

    @Test
    void findByUsername() {
        //given
        Member member = new Member("abc", "def", "nick", "ROLE_USER");
        memberRepository.save(member);

        //when
        Optional<Member> findMemberByUsername = memberRepository.findMemberByUsername("abc");
        Member savedMember = findMemberByUsername.get();

        //then
        assertThat(findMemberByUsername).isNotEmpty();
        assertThat(savedMember.getUsername()).isEqualTo("abc");
        assertThat(savedMember.getPassword()).isEqualTo("def");
        assertThat(savedMember.getNickname()).isEqualTo("nick");
        assertThat(savedMember.getAuthority()).isEqualTo("ROLE_USER");

    }
}