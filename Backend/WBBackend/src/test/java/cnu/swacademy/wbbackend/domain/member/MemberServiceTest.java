package cnu.swacademy.wbbackend.domain.member;

import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.repository.MemberRepository;
import cnu.swacademy.wbbackend.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setup() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("MemberService 를 통하여 멤버를 저장할 수 있다.")
    void save() {
        //given
        String username = "mockUser1";
        String password = "password";
        String nickname = "nick1";
        String authority = "ROLE_USER";
        Member member = new Member(username, password, nickname, authority);

        //when
        Member save = memberService.save(member);

        //then
        assertThat(save.getUsername()).isEqualTo(username);
        assertThat(new BCryptPasswordEncoder().matches(password, save.getPassword())).isEqualTo(true);
        assertThat(save.getNickname()).isEqualTo(nickname);
        assertThat(save.getAuthority()).isEqualTo(authority);
    }

    @Test
    @DisplayName("MemberService 를 통하여 memberId 를 통해 멤버를 조회할 수 있다.")
    void findById() {
        //given
        String username = "mockUser1";
        String password = "password";
        String nickname = "nick1";
        String authority = "ROLE_USER";
        Member member = new Member(username, password, nickname, authority);
        Member saved = memberService.save(member);
        Long memberId = saved.getId();

        //when
        Optional<Member> findById = memberService.findById(memberId);
        /* securityConfig 의 mock 객체에 의해 2L 로 insert 됨.
            BeforeEach 의 setup 메소드에서 제거해도 2L 로 insert.
         */

        //then
        assertThat(findById).isNotEmpty();
        Member save = findById.get();

        assertThat(save.getUsername()).isEqualTo(username);
        assertThat(new BCryptPasswordEncoder().matches(password, save.getPassword())).isEqualTo(true);
        assertThat(save.getNickname()).isEqualTo(nickname);
        assertThat(save.getAuthority()).isEqualTo(authority);

    }
}
