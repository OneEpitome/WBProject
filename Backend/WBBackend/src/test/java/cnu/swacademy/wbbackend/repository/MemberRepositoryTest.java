package cnu.swacademy.wbbackend.repository;

import cnu.swacademy.wbbackend.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findByUsername_ExistingUsername_ReturnsMember() {
        // given
        Member member = new Member("testUser", "testPassword", "testNickname");
        testEntityManager.persist(member);

        // when
        Optional<Member> foundMember = memberRepository.findByUsername("testUser");

        // then
        assertThat(foundMember.isPresent()).isTrue();
        assertThat(foundMember.get()).isEqualTo(member);
    }

    @Test
    void findByUsername_NonExistingUsername_ReturnsEmptyOptional() {
        // given
        Member member = new Member("testUser", "testPassword", "testNickname");
        testEntityManager.persist(member);

        // when
        Optional<Member> foundMember = memberRepository.findByUsername("nonExistingUser");

        // then
        assertThat(foundMember.isPresent()).isFalse();
    }
}