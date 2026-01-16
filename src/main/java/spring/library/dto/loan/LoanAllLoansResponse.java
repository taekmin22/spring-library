package spring.library.dto.loan;

import lombok.Builder;
import lombok.Data;
import spring.library.domain.Loan;

@Data
@Builder
public class LoanAllLoansResponse {
    private Long bookId;
    private String title;
    private String author;
    private String loanDate;
    private String dueDate;
    private Long renewalCount;

    public static LoanAllLoansResponse from(Loan loan) {
        return builder()
                .bookId(loan.getBook().getId())
                .title(loan.getBook().getTitle())
                .author(loan.getBook().getAuthor())
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .renewalCount(loan.getRenewalCount())
                .build();
    }
}
