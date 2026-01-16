package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.member.MemberRequest;
import spring.library.dto.member.MemberResponse;
import spring.library.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public MemberResponse createMember(@RequestBody MemberRequest memberRequest) {
        return memberService.createMember(memberRequest);
    }

    @GetMapping
    public List<MemberResponse> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping("/{memberId}")
    public MemberResponse updateMember(@PathVariable Long memberId, @RequestBody MemberRequest memberRequest) {
        return memberService.updateMember(memberRequest, memberId);
    }

    @DeleteMapping("/{memberId}")
    public MemberResponse deleteMember(@PathVariable Long memberId) {
        return memberService.deleteMember(memberId);
    }
}
