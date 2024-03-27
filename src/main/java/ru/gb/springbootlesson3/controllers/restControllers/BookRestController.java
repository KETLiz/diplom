package ru.gb.springbootlesson3.controllers.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.services.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("book")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.findBookById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> removeBookById(@PathVariable long id) {
        bookService.removeBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody BookRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addNewBook(request));
        } catch(NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
