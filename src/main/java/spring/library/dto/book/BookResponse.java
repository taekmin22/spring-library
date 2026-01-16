package spring.library.dto.book;

import lombok.Builder;
import lombok.Data;
import spring.library.domain.Book;

@Data
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private String classification;
    private String status;
    private Long amount;

    public static BookResponse from(Book book) {
        return builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .publicationYear(book.getPublicationYear())
                .classification(book.getClassification())
                .status(book.getStatus())
                .amount(book.getAmount())
                .build();
    }
}
