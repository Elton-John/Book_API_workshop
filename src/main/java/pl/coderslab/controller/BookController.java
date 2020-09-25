package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.interfacies.BookService;

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

//    @PostMapping
//    public void addBook(@RequestBody Book book) {              //to nie działa
//        bookService.addBook(book);
//    }


    @PostMapping
    public void addBook(@RequestParam String isbn,
                        @RequestParam String title,
                        @RequestParam String author,
                        @RequestParam String publisher,
                        @RequestParam String type) {
        Book book = new Book(0L, isbn, title, author, publisher, type);
        bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElseThrow(() -> new NoSuchElementException("No such element"));
    }

    @PutMapping("/{id}")
    public void updateBookById(@PathVariable long id,
                               @RequestParam String isbn,
                               @RequestParam String title,
                               @RequestParam String author,
                               @RequestParam String publisher,
                               @RequestParam String type) {
        Book newBook = new Book(id, isbn, title, author, publisher, type);
        bookService.updateBookById(id, newBook);
    }
}