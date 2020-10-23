package pl.coderslab.books;

import pl.coderslab.books.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks();

    void addBook(Book book);

    Optional<Book> getBookById(Long id);

    void updateBookById(Book book);

    void deleteBookById(Long id);
}
