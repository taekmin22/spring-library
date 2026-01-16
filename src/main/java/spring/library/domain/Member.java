package spring.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.library.dto.member.MemberRequest;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private Long idNumber;

    @Column(nullable = false, length = 50)
    private String feature;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String phoneNumber;

    @OneToMany(mappedBy = "member")
    private List<Loan> loans = new ArrayList<>();

    public static Member from(MemberRequest memberRequest) {
        return builder()
                .name(memberRequest.getName())
                .idNumber(memberRequest.getIdNumber())
                .feature(memberRequest.getFeature())
                .email(memberRequest.getEmail())
                .phoneNumber(memberRequest.getPhoneNumber())
                .build();
    }

    public void update(MemberRequest memberRequest) {
        this.name = memberRequest.getName();
        this.idNumber = memberRequest.getIdNumber();
        this.feature = memberRequest.getFeature();
        this.email = memberRequest.getEmail();
        this.phoneNumber = memberRequest.getPhoneNumber();
    }
}
