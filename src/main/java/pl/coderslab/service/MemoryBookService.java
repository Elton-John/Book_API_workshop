package pl.coderslab.service;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;
import pl.coderslab.interfacies.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryBookService implements BookService {
    private List<Book> books;
    private static Long nextId;

    public MemoryBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
        nextId = 4L;
    }

    @Override
    public List<Book> getBooks() {
        return this.books;
    }

    @Override
    public void addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Override
    public void updateBookById(Long id, Book newBook) {
        getBookById(id).ifPresent(book -> {
            int index = books.indexOf(book);
            newBook.setId(id);
            books.set(index, newBook);
        });
    }

    @Override
    public void deleteBookById(Long id) {
        getBookById(id).ifPresent(book -> books.remove(book));
    }
}
