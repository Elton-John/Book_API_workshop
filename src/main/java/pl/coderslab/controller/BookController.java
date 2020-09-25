package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.interfacies.BookService;

import java.util.List;

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
//    public void addBook(@RequestBody Book book){              //to nie działa
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
}