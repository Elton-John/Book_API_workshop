package pl.coderslab.books;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping
    public void addBook(@RequestBody Book book) {              //to nie działa
        bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "entity not found");
                }
        );
    }

    @PutMapping
    public void updateBookById(@RequestBody Book newBook) {
        bookService.updateBookById(newBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id) {
        bookService.deleteBookById(id);
    }
}