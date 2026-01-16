package spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findAllByMemberAndIsReturnedIsFalse(Member member);

    List<Loan> findAllByMember(Member member);

    Loan findByMemberAndBook(Member member, Book book);
}
