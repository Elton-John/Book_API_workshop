package pl.coderslab.service;

import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;
import pl.coderslab.interfacies.BookService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FileBookService implements BookService {
    private static long nextId;

    public FileBookService() {

        try {
            FileInputStream fis = new FileInputStream("books.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int lastIndex = ois.readInt();
            Long lastId = getBooks().get(lastIndex).getId();
            nextId = lastId+1;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("books.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int bookCount = ois.readInt();
            for (int i = 0; i < bookCount; i++) {
                Book book = (Book) ois.readObject();
                books.add(book);
            }
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

            try {
                FileOutputStream fos = new FileOutputStream("books.bin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeInt(books.size());
                    books.forEach(book1 -> {
                        try {
                            oos.writeObject(book1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.empty();
    }

    @Override
    public void updateBookById(Long id, Book book) {

    }

    @Override
    public void deleteBookById(Long id) {

    }
}
