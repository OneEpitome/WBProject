package cnu.swacademy.wbbackend.domain.member.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterMemberForm {
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password;

    @NotBlank(message = "별명은 공백일 수 없습니다.")
    private String nickname;
}
