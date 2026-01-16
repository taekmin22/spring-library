package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Member;
import spring.library.dto.member.MemberRequest;
import spring.library.dto.member.MemberResponse;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse createMember(MemberRequest req) {
        if (memberRepository.existsByIdNumber(req.getIdNumber())) {
            throw new IllegalArgumentException((req.getIdNumber() + " already exists"));
        }
        Member member = memberRepository.save(Member.from(req));
        return MemberResponse.from(member);
    }

    public List<MemberResponse> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberResponse::from).toList();
    }

    public MemberResponse updateMember(MemberRequest memberRequest, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        member.update(memberRequest);
        return MemberResponse.from(member);
    }

    public MemberResponse deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
        MemberResponse memberResponse = MemberResponse.from(member);
        memberRepository.deleteById(memberId);
        return memberResponse;
    }
}
