package spring.library.dto.member;

import lombok.Data;

@Data
public class MemberRequest {

    private String name;

    private Long idNumber;

    private String feature;

    private String email;

    private String phoneNumber;
}
