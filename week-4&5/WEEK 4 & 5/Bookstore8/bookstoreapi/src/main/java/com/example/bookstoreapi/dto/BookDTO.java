package com.example.bookstoreapi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BookDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 1, max = 50)
    private String author;
}
