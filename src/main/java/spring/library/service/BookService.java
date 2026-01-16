package spring.library.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.domain.Book;
import spring.library.dto.book.BookRequest;
import spring.library.dto.book.BookResponse;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookResponse addBook(BookRequest bookRequest) {
        Book book = bookRepository.save(Book.from(bookRequest));
        return BookResponse.from(book);
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookResponse::from).toList();
    }

    public BookResponse updateBook(BookRequest bookRequest, Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        book.update(bookRequest);
        return BookResponse.from(book);
    }

    public BookResponse deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        bookRepository.delete(book);
        return BookResponse.from(book);
    }
}
