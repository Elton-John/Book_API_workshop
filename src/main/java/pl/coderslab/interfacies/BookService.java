package pl.coderslab.interfacies;

import pl.coderslab.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    void addBook(Book book);

    Book getBookById(Long id);

    void updateBookById(Long id);

    void deleteBookById(Long id);
}
