package pl.coderslab.books;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Primary
public class JpaBookService implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.empty();
    }

    @Override
    public void updateBookById(Book book) {

    }

    @Override
    public void deleteBookById(Long id) {

    }
}