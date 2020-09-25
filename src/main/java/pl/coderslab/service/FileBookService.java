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
            int lastIndex = ois.readInt() - 1;
            nextId = getBooks().get(lastIndex).getId() + 1;
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
           books =(List<Book>) ois.readObject();
//            int bookCount = ois.readInt();
//            for (int i = 0; i < bookCount; i++) {
//                Book book = (Book) ois.readObject();
//                books.add(book);
//            }
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
            List<Book> books = getBooks();
            int index = books.indexOf(book);
            newBook.setId(id);
            books.set(index, newBook);
            reWrite(books);
        });
    }

    @Override
    public void deleteBookById(Long id) {
        getBookById(id).ifPresent(book -> {
            List<Book> books = getBooks();
            books.remove(book);
            reWrite(books);
        });

    }

    private void writeToFile(List<Book> books) {
        try {
            FileOutputStream fos = new FileOutputStream("books.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
//            oos.writeInt(books.size());
//            books.forEach(book1 -> {
//                try {
//                    oos.writeObject(book1);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reWrite(List<Book> books) {

        try {
            FileOutputStream fos = new FileOutputStream("books.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.reset();
            writeToFile(books);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
