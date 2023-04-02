package main.ex4.repo;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * The class that represents a book.
 */
@Entity
public class Book {
    /**
     * the id of the book.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * the name of the book.
     */
    @NotEmpty(message = "Name is mandatory")
    private String name;

    /**
     * the price of the book.
     */
    @NotNull(message = "The price must be a number")
    @Min(1)
    private Double price;

    /**.
     * the url of the imag eof the book
     */
    private String url;

    /**
     * the discount of the book.
     */
    @Min(0)
    private Double discount;

    /**
     * the quantity of the book in stock.
     */
    @NotNull(message = "Quantity is mandatory")
    @Min(0)
    private Integer quantity;

    /**
     * A constructor.
     */
    public Book() {}

    /**
     * A constructor.
     * @param name - the name of the book we will set.
     * @param price - the price of the book we will set.
     * @param url - the url of the book we will set.
     * @param discount - the discount of the book we will set.
     * @param quantity - the quantity of the book we will set.
     */
    public Book(String name, Double price,String url,Double discount,Integer quantity) {

        this.name = name;
        this.price = price;
        this.url=url;
        this.discount=discount;
        this.quantity = quantity;

    }

    /**
     * The function that checks if the url is correct.
     * @param image_path - a given url
     * @return - true if the url is good, or gfalse if not.
     */
    public boolean exists( String image_path) {
        return image_path.matches("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)");
    }

    /**
     * This function sets the name of the book.
     * @param name - the name we will set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A function that gets the name of the book.
     * @return - the name of the book.
     */
    public String getName() {
        return name;
    }

    /**
     * A function that gets the url of the book.
     * @return - the url of the book.
     */
    public String getUrl() {
        return url;
    }

    /**
     * A function that gets the price of the book.
     * @return - the price of the book.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * A function that gets the quantity of the book.
     * @return - the quantity of the book.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * A function that gets the discount of the book.
     * @return - the name of the discount.
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * A function that gets the id of the book.
     * @return - the id of the book.
     */
    public Long getId() {
        return id;
    }

    /**
     * This function sets the price of the book.
     * @param price - the price we will set.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This function sets the url of the book.
     * @param url - the url we will set.
     */
    public void setUrl(String url)
    {
        if(exists(url))
            this.url=url;
        else
            this.url = "default_book.jpg";
    }
    /**
     * This function sets the discount of the book.
     * @param discount - the discount we will set.
     */

    public void setDiscount(Double discount)
    {
        if(discount==null)
            this.discount=0.0;
        else
            this.discount=discount;
    }
    /**
     * This function sets the quantity of the book.
     * @param quantity - the quantity we will set.
     */

    public void setQuantity(Integer quantity)
    {
        this.quantity=quantity;
    }
    /**
     * This function sets the id of the book.
     * @param id - the id we will set.
     */


    public void setId(Long id)
    {
        this.id=id;
    }


    /**
     * This function is override to toString function .
     * @return  string of the data.
     */

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", url=" + url +
                ", price=" + price +  ", discount=" + discount +", quantity=" + quantity+'}';
    }

}