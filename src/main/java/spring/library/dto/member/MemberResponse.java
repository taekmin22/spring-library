package spring.library.dto.member;

import lombok.Builder;
import lombok.Data;
import spring.library.domain.Member;

@Data
@Builder
public class MemberResponse {
    private Long id;

    private String name;

    private Long idNumber;

    private String feature;

    private String email;

    private String phoneNumber;

    public static MemberResponse from(Member member) {
        return builder()
                .id(member.getId())
                .name(member.getName())
                .idNumber(member.getIdNumber())
                .feature(member.getFeature())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
