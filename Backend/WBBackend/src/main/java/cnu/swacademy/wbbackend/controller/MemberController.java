package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.dto.MemberResponseDTO;
import cnu.swacademy.wbbackend.entity.Member;
import cnu.swacademy.wbbackend.service.MemberService;
import cnu.swacademy.wbbackend.model.RegisterMemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {
    private final MemberService memberService;


    @PostMapping
    public ResponseEntity<Object> register(@Validated @RequestBody RegisterMemberForm registerMemberForm,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            log.info("에러 내역 : {}", fieldErrors);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Member member = new Member(registerMemberForm.getUsername(),
                registerMemberForm.getPassword(), registerMemberForm.getNickname());

        Member savedMember = memberService.save(member);
        MemberResponseDTO memberDto = new MemberResponseDTO(savedMember.getId(),
                savedMember.getUsername(),
                savedMember.getNickname(),
                savedMember.getReviewList(),
                savedMember.getAuthorities());
        return ResponseEntity.ok(memberDto);
    }


    // 본인의 정보만 가져올 수 있도록 SpringSecurity Authorization 필요.
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDTO> readMember(@PathVariable Long memberId) {
        Member member = memberService.findById(memberId);

        MemberResponseDTO memberDto = new MemberResponseDTO(member.getId(), member.getUsername(), member.getNickname(),
                member.getReviewList(), member.getAuthorities());

        return ResponseEntity.ok().body(memberDto);
    }

}
