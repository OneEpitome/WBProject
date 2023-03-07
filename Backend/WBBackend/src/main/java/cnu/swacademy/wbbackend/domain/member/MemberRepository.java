package cnu.swacademy.wbbackend.domain.member;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class MemberRepository {
    private static Map<Long, Member> repository = new HashMap<>();
    private static Long sequence = 0L;
    public Member save(Member member) {
        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        repository.put(++sequence, member);
        log.info("Member Created, username : {}", member.getUsername());
        return member;
    }

    public Optional<Member> findByUsername(String username) {
        return repository.values()
                .stream().filter(m -> m.getUsername().equals(username))
                .findFirst();
    }

    public void clear() {
        repository.clear();
    }
}
