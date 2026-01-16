package spring.library.dto.book;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String author;
    private String publisher;
    private Long publicationYear;
    private String classification;
    private String status;
    private Long amount;
}
