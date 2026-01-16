package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.book.BookRequest;
import spring.library.dto.book.BookResponse;
import spring.library.dto.loan.LoanAllLoansResponse;
import spring.library.dto.loan.LoanCreateRequest;
import spring.library.dto.loan.LoanMyLoansResponse;
import spring.library.service.BookService;
import spring.library.service.LoanService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final LoanService loanService;

    @PostMapping
    public BookResponse addBook(@RequestBody BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/{bookId}")
    public BookResponse updateBook(@PathVariable Long bookId, @RequestBody BookRequest bookRequest) {
        return bookService.updateBook(bookRequest, bookId);
    }

    @DeleteMapping("/{bookId}")
    public BookResponse deleteBook(@PathVariable Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @PostMapping("/{bookId}/checkout")
    public LoanAllLoansResponse createLoan(@PathVariable Long bookId, @RequestBody LoanCreateRequest loanRequest) {
        return loanService.createLoan(bookId, loanRequest);
    }

    @GetMapping("/checkout")
    public List<LoanAllLoansResponse> getAllLoans(@RequestParam("memberId") Long memberId) {
        return loanService.getAllLoans(memberId);
    }

    @GetMapping("/history")
    public List<LoanMyLoansResponse> getLoanHistory(@RequestParam("memberId") Long memberId) {
        return loanService.getLoanHistory(memberId);
    }

    @PutMapping("/{bookId}/return")
    public LoanMyLoansResponse returnBook(@PathVariable Long bookId, @RequestBody LoanCreateRequest loanRequest) {
        return loanService.returnLoan(bookId, loanRequest);
    }

    @PutMapping("/{bookId}/renewal")
    public LoanMyLoansResponse renewLoan(@PathVariable Long bookId, @RequestBody LoanCreateRequest loanRequest) {
        return loanService.renewLoan(bookId, loanRequest);
    }



}
