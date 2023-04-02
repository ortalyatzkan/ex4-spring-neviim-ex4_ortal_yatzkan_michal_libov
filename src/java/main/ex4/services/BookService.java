package main.ex4.services;

import main.ex4.repo.Book;
import main.ex4.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * A service class of book.
 */

@Service
public class BookService {

    /**
     * the repository
     */
    @Autowired
    private BookRepository repository;

    /**
     * this function adds a book.
     * @param books - the book we will add.
     */
    @Transactional
    public void addBooks(ArrayList<Book> books) {
        for (Book book : books) {
            repository.save(book);
        }
    }

    /**
     * A function that save book on data base.
     * @param book-book to save.
     */

    public void saveBook(Book book) {
        repository.save(book);
    }

    /**
     * A function that delete book from data base.
     * @param u-book to delete.
     */

    public void deleteBook(Book u) {
        repository.delete(u);
    }

    /**
     * A function that return book from database by id.
     * @param id- id of book.
     * @return book from data base.
     */
    public Optional<Book> getBook(Long id) {
        return repository.findById(id);
    }

    /**
     * A function that return book by name.
     * @param name-name of book that return.
     * @return - the book.
     */
    public List<Book> getBookByName(String name) {
        return repository.findByName(name);
    }

    /**
     *A get function that return book discount.
     * @return - discount of book;
     */

    public List<Book> getDiscount() {
        List<Book>temp=repository.findAll();
        TreeMap<Double,Long>listPrice= new TreeMap<Double,Long>();
        List<Book>result=new ArrayList<>();


        for (int i=0; i<temp.size(); i++) {
            listPrice.put(temp.get(i).getPrice()-temp.get(i).getPrice()*temp.get(i).getDiscount()/100, temp.get(i).getId());
        };
        for( Map.Entry<Double,Long> entry : listPrice.entrySet()) {
            result.add(repository.findById(entry.getValue()).get());
            for(int i=0; i<temp.size(); i++) {
                if(entry.getKey()==temp.get(i).getPrice()-temp.get(i).getPrice()*temp.get(i).getDiscount()/100 && entry.getValue()!=temp.get(i).getId())
                    result.add(temp.get(i));

            }
        }

        if(result.size() < 5)
            return result;
        return result.subList(0, 5);
    }

    /**
     * A function that return all books in database.
     * @return All books in database.
     */
    public List<Book> getBooks() {
        return repository.findAll();
    }
}
