package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;
import spring.library.dto.loan.LoanAllLoansResponse;
import spring.library.dto.loan.LoanCreateRequest;
import spring.library.dto.loan.LoanMyLoansResponse;
import spring.library.repository.BookRepository;
import spring.library.repository.LoanRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional
public class LoanService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;

    public LoanAllLoansResponse createLoan(Long bookId, LoanCreateRequest loanCreateRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Member member = memberRepository.findById(loanCreateRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        LoanAllLoansResponse loanResponse = null;
        if(book.getStatus().equals("대출가능")) {
            LocalDate localDate = LocalDate.now();
            int loanLimit = 100;
            LocalDate dueDate = localDate;
            if (member.getFeature().equals("학생")) {
                dueDate = localDate.plusDays(10);
                loanLimit = 10;
            }
            else if (member.getFeature().equals("교수")) {
                dueDate = localDate.plusDays(30);
                loanLimit = 20;
            }
            else {
                dueDate = localDate.plusDays(110813);
            }

            if (loanRepository.findAllByMember(member).size() < loanLimit) {
                throw new InputMismatchException("대출 한도를 초과했습니다.");
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = localDate.format(formatter);
                String dDate = dueDate.format(formatter);
                Loan loan = Loan.builder()
                        .loanDate(date)
                        .dueDate(dDate)
                        .renewalCount(0L)
                        .isReturned(false)
                        .member(member)
                        .book(book)
                        .build();
                loanRepository.save(loan);
                loanResponse = LoanAllLoansResponse.from(loan);
            }
        }
        else {
            throw new InputMismatchException("대출불가능");
        }
        return loanResponse;
    }

    public List<LoanAllLoansResponse> getAllLoans(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        List<Loan> loans = loanRepository.findAllByMemberAndIsReturnedIsFalse(member);
        return loans.stream().map(LoanAllLoansResponse::from).toList();
    }

    public List<LoanMyLoansResponse> getLoanHistory(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        List<Loan> loans = loanRepository.findAllByMember(member);
        return loans.stream().map(LoanMyLoansResponse::from).toList();
    }

    public LoanMyLoansResponse returnLoan(@PathVariable Long bookId, LoanCreateRequest loanCreateRequest) {
        Member member = memberRepository.findById(loanCreateRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Loan loan = loanRepository.findByMemberAndBook(member, book);
        loan.returnLoan();
        return LoanMyLoansResponse.from(loan);
    }

    public LoanMyLoansResponse renewLoan(Long bookId, LoanCreateRequest loanRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Member member = memberRepository.findById(loanRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Loan loan = loanRepository.findByMemberAndBook(member, book);
        if (loan.getRenewalCount() == 0) {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (loan.getDueDate().equals(localDate.format(formatter))) {
                loan.renewal();
            }
            else {
                throw new InputMismatchException("연장 가능 날짜가 아닙니다.");
            }
        }
        else {
            throw new InputMismatchException("이미 연장한 책입니다.");
        }
        return LoanMyLoansResponse.from(loan);
    }


}
