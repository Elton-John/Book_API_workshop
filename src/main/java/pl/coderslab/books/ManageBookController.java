package pl.coderslab.books;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/books")
public class ManageBookController {

    private final BookService bookService;

    @GetMapping
    public String index(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/add")
    public String showBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "/books/add";
    }

    @PostMapping("/add")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:all";
    }

    @GetMapping("/{id}")
    public String showBookById(@PathVariable Long id, Model model) {
        Book book = getBookByIdIfExistsOrThrowException(id);
        model.addAttribute("book", book);
        return "/books/book";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        Book book = getBookByIdIfExistsOrThrowException(id);
        model.addAttribute("book", book);
        return "/books/add";
    }

    public Book getBookByIdIfExistsOrThrowException(Long id) {
        return bookService.getBookById(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("/submit/{id}")
    public String submitDeleting(@PathVariable Long id, Model model) {
        Book book = getBookByIdIfExistsOrThrowException(id);
        model.addAttribute("title", book.getTitle());
        model.addAttribute("id", id);
        return "/books/submit";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        getBookByIdIfExistsOrThrowException(id);
        bookService.deleteBookById(id);
        return "redirect:/admin/books/all";
    }
}
