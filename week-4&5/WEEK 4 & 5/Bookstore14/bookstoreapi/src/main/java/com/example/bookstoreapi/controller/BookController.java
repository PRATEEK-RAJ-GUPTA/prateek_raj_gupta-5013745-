package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.assembler.BookResourceAssembler;
import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookResourceAssembler bookResourceAssembler;

    @GetMapping
    public CollectionModel<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOs = bookRepository.findAll().stream()
                .map(bookResourceAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(bookDTOs, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        // Assume a method to fetch a book by id and map it to a BookDTO
        BookDTO bookDTO = ...;
        return ResponseEntity.ok(bookDTO);

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.bookDTOToBook(bookDTO);
        Book savedBook = bookRepository.save(book);
        BookDTO savedBookDTO = bookResourceAssembler.toModel(savedBook);
        return ResponseEntity.ok(savedBookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        Book updatedBook = bookRepository.save(book);
        BookDTO updatedBookDTO = bookResourceAssembler.toModel(updatedBook);
        return ResponseEntity.ok(updatedBookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }
    @Test
void shouldCreateNewBook() throws Exception {
    Book newBook = new Book(null, "New Book", "New Author");
    Book savedBook = new Book(1L, "New Book", "New Author");

    when(bookService.saveBook(newBook)).thenReturn(savedBook);

    mockMvc.perform(post("/api/books")
            .contentType("application/json")
            .content("{\"title\": \"New Book\", \"author\": \"New Author\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.title").value("New Book"))
            .andExpect(jsonPath("$.author").value("New Author"));
}

}
