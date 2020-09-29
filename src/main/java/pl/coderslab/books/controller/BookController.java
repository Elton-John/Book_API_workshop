package pl.coderslab.books.controller;


import org.springframework.web.bind.annotation.*;
import pl.coderslab.books.entity.Book;
import pl.coderslab.books.interfacies.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {              //to nie działa
        bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElseThrow(() -> new NoSuchElementException("No such element"));
    }

    @PutMapping
    public void updateBookById( @RequestBody Book newBook) {
        bookService.updateBookById(newBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id) {
        bookService.deleteBookById(id);
    }
}