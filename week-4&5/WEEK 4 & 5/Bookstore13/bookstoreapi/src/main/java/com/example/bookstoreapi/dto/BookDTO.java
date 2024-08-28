package com.example.bookstoreapi.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

@Data
public class BookDTO extends RepresentationModel<BookDTO> {
    private Long id;
    private String title;
    private String author;
}
