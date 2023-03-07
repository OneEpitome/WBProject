package cnu.swacademy.wbbackend.domain.member;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "doesn't exist in MemberRepository"));
        User user = new User(username, member.getPassword(), AuthorityUtils.createAuthorityList(member.getAuthority()));
        return user;
    }

    // MockMember for Dev
    @PostConstruct
    void setup() {
        memberRepository.save(new Member("user", "123", "user", "USER_ROLE"));
    }
}
