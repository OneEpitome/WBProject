package cnu.swacademy.wbbackend.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "doesn't exist in MemberRepository"));
        User user = new User(username, member.getPassword(), AuthorityUtils.createAuthorityList(member.getAuthority()));
        return user;
    }

    public Member save(Member member) {
        member.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
        member.setAuthority("ROLE_USER");
        memberRepository.save(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }
}
