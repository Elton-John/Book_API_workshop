package pl.coderslab.service;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;
import pl.coderslab.interfacies.BookService;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FileBookService implements BookService {
    private static List<Book> books;
    private static long nextId;
    private static List<Long> idNumbers;

    public FileBookService() {
        idNumbers = new ArrayList<>();
        getBooks().forEach(book -> idNumbers.add(book.getId()));
        Long maxId = idNumbers.stream().max(Long::compareTo).orElseGet(() -> 0L);
        nextId = maxId + 1;
    }

    @Override
    public List<Book> getBooks() {
        books = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("books.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            books = (List<Book>) ois.readObject();
            books.sort(Comparator.comparing(Book::getId));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void addBook(Book book) {
        List<Book> books = getBooks();
        book.setId(nextId++);
        books.add(book);
        writeToFile(books);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return getBooks().stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    @Override
    public void updateBookById(Long id, Book newBook) {
        getBookById(id).ifPresent(book -> {
            books.remove(book);
            newBook.setId(id);
            books.add(newBook);
            writeToFile(books);
        });
    }

    @Override
    public void deleteBookById(Long id) {
        getBookById(id).ifPresent(book -> {
            books.remove(book);
            writeToFile(books);
        });

    }

    private void writeToFile(List<Book> books) {
        try {
            FileOutputStream fos = new FileOutputStream("books.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
