package pl.coderslab.interfacies;

import pl.coderslab.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks();

    void addBook(Book book);

    Optional<Book> getBookById(Long id);

    void updateBookById(Long id);

    void deleteBookById(Long id);
}
