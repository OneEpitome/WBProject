package cnu.swacademy.wbbackend.controller;

import cnu.swacademy.wbbackend.domain.member.Member;
import cnu.swacademy.wbbackend.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public Member createMember(@ModelAttribute Member member) {
        Member save = memberService.save(member);
        return save;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Object> readMember(@PathVariable Long memberId) {
        Optional<Member> optionalMember = memberService.findById(memberId);

        if (optionalMember.isEmpty()) {
            return ResponseEntity.badRequest().body("Member Not Found");
        }

        Member member = optionalMember.get();
        Map<String, Object> memberInfo = new HashMap<>();
        memberInfo.put("username", member.getUsername());
        memberInfo.put("nickname", member.getNickname());
        memberInfo.put("reviewList", member.getReviewList());
        memberInfo.put("authority", member.getAuthority());

        /*
        * 멤버 조회 시 리턴하는 엔티티 새로 만들어야 할 듯
        * */

        return ResponseEntity.ok().body(memberInfo);
    }
}
