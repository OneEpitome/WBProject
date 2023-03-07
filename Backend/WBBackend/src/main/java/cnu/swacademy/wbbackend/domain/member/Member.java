package cnu.swacademy.wbbackend.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long Id;
    private String username;
    private String password;
    private String nickname;
    private String authority;
}
