package pl.coderslab.service;

import pl.coderslab.entity.Book;
import pl.coderslab.interfacies.BookService;

import java.util.List;

public class MemoryBookService implements BookService {
    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public void updateBookById(Long id) {

    }

    @Override
    public void deleteBookById(Long id) {

    }
}
