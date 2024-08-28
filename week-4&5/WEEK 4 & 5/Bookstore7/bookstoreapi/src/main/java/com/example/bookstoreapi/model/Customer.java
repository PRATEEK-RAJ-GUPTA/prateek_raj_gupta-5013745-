package com.example.bookstoreapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties during deserialization
public class Customer {
    private Long id;

    @JsonProperty("full_name") // Custom JSON property name
    private String name;

    private String email;
}
