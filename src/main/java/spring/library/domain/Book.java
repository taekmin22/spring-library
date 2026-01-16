package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.dto.book.BookRequest;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 50)
    private String publisher;

    private Long publicationYear;

    @Column(nullable = false, length = 50)
    private String classification;

    @Column(nullable = false, length = 50)
    private String status;

    private Long amount;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans = new ArrayList<>();

    public static Book from(BookRequest bookRequest) {
        return builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .publicationYear(bookRequest.getPublicationYear())
                .classification(bookRequest.getClassification())
                .status(bookRequest.getStatus())
                .amount(bookRequest.getAmount())
                .build();
    }

    public void update(BookRequest bookRequest) {
        title = bookRequest.getTitle();
        author = bookRequest.getAuthor();
        publisher = bookRequest.getPublisher();
        publicationYear = bookRequest.getPublicationYear();
        classification = bookRequest.getClassification();
        status = bookRequest.getStatus();
        amount = bookRequest.getAmount();
    }

}
