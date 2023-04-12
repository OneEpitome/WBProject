package cnu.swacademy.wbbackend.dto;

import cnu.swacademy.wbbackend.entity.Review;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberResponseDTO {
    private Long id;
    private String username;
    private String nickname;
    private List<Review> reviewList;
    private Collection<? extends GrantedAuthority> authorities;

    public MemberResponseDTO(Long id, String username, String nickname, List<Review> reviewList, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.reviewList = reviewList;
        this.authorities = authorities;
    }
}
