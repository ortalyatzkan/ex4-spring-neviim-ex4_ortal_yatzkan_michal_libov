package main.ex4.beans;

import lombok.Getter;
import lombok.Setter;
import main.ex4.repo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that represents the books in the cart. It works as a session.
 */
@Getter
@Setter
@Component
public class BooksInCart implements Serializable {

    /**
     * the books that are in the cart.
     */
    private ArrayList<Book> booksInCart;

    /**
     * A constructor.
     */
    public BooksInCart() {

        this.booksInCart = new ArrayList<Book>();
    }

    /**
     * A fucntion that gets a certain book.
     * @param id - the id of the book we want.
     * @return - the wanted book.
     */
    public Book getBook(long id) {

       for(Book book:booksInCart)
       {
           if(id==book.getId())
               return book;
       }
       Book b=new Book();
       return b;
    }

    /**
     * A function that deletes a book from cart.
     * @param id - the id of the book we want to delete.
     */
    public void deleteFromCart(long id)
    {
        for(int i=0; i<booksInCart.size(); i++)
        {
            if(booksInCart.get(i).getId() ==id) {
                if (booksInCart.get(i).getQuantity() == 1) {
                    booksInCart.remove(booksInCart.get(i));
                    break;
                }
                booksInCart.get(i).setQuantity(booksInCart.get(i).getQuantity() - 1);
            }
        }
    }

    /**
     * A function that empties the cart.
     */
    public void emptyCart()
    {
        booksInCart.clear();
    }

    /**
     * A function that returns all the books that are in the cart.
     * @return - all the books in the cart.
     */
    public ArrayList<Book> getBooksInCart() {
        return booksInCart;
    }

    /**
     * A function that sets the books in the cart.
     * @param books - a list we will compare our member to.
     */
    public void setBooksInCart(ArrayList<Book> books) {
        this.booksInCart = books;
    }

    /**
     * A function that returns the amount of books that are in the cart.
     * @return - the amount of books that are in the cart.
     */
    public int amountOfItemsInCart(){
        int amount = 0;
        for(Book book : booksInCart)
            amount += book.getQuantity();

        return amount;
    }

    /**
     * This function adds a new book to the cart.
     * @param book - the book we want to add to the cart.
     */
    public void add (Book book) {

        for(Book b : booksInCart){
            if(b.getId().equals(book.getId())){
                b.setQuantity(b.getQuantity()+1);
                return;
            }
        }
        Book b = new Book(book.getName(), book.getPrice(), book.getUrl(), book.getDiscount(), 1);
        b.setId(book.getId());
        booksInCart.add(b);
    }


}

