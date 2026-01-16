package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByIdNumber(Long idNumber);
}

