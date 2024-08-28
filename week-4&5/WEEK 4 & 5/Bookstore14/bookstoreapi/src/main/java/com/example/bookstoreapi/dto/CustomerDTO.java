package com.example.bookstoreapi.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

@Data
public class CustomerDTO extends RepresentationModel<CustomerDTO> {
    private Long id;
    private String name;
    private String email;
}
