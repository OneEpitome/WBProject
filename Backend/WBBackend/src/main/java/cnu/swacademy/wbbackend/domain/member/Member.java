package cnu.swacademy.wbbackend.domain.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long Id;
    private String username;
    private String password;
    private String nickname;
    private String authority;

    public Member(String username, String password, String nickname, String authority) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
    }
}
