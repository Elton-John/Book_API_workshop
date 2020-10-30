package pl.coderslab.books;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/new")
    public String newBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/new";
    }

    @PostMapping("/new")
    public String create(Book book) {
        bookService.addBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Book book = getBookByIdIfExistsOrThrowException(id);
        model.addAttribute("book", book);
        return "/books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Book book = getBookByIdIfExistsOrThrowException(id);
        model.addAttribute("book", book);
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Book book) {
        getBookByIdIfExistsOrThrowException(id);
        bookService.updateBookById(book);
        return "redirect:/admin/books";
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

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        getBookByIdIfExistsOrThrowException(id);
        bookService.deleteBookById(id);
        return "redirect:/admin/books";
    }
}
