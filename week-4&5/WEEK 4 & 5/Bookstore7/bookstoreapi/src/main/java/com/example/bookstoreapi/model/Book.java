package com.example.bookstoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties during deserialization
public class Book {
    private Long id;

    @JsonProperty("book_title") // Custom JSON property name
    private String title;

    private String author;
}
