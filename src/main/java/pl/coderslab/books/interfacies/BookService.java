package pl.coderslab.books.interfacies;

import pl.coderslab.books.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks();

    void addBook(Book book);

    Optional<Book> getBookById(Long id);

    void updateBookById(Book book);

    void deleteBookById(Long id);
}
