package main.ex4.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Book repository interface
 */

/* default scope of this Bean is "singleton" */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * This function return the books that have the same name as given.
     * @param name - the book's name we want to find.
     * @return - all the books with this name.
     */
    List<Book> findByName(String name);


}

