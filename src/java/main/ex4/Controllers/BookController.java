package main.ex4.Controllers;
import main.ex4.beans.BooksInCart;
import main.ex4.listeners.SessionListenerCounter;
import main.ex4.repo.PaymentTracker;
import main.ex4.repo.SearchName;
import main.ex4.services.PaymentTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import main.ex4.repo.Book;
import main.ex4.services.BookService;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The book controller class.
 */
@Controller
public class BookController {
    /**
     * The book service member.
     */
    @Autowired
    private BookService bookService;

    /**
     * the payment trackerservice member.
     */
    @Autowired
    private PaymentTrackerService paymentTrackerService;

    /**
     * the session listener.
     */
    @Resource(name="sessionListenerWithMetrics")
    private ServletListenerRegistrationBean<SessionListenerCounter> metrics;

    /**
     * the books that are in the chart member.
     */
    @Autowired
    private BooksInCart booksInCart;

    /**
     * The function that gets the book service.
     * @return the book service.
     */
    public BookService getBookService() {return bookService;}

    /**
     * The main function of the landing page.
     * @param book - a book we will display in the shop.
     * @param model - the model that allows us to add attribute.
     * @param searchName - the search bar that allows us to search books.
     * @return - the index page.
     */
    @GetMapping("/")
    public String main( Book book,Model model,@ModelAttribute("SearchName") SearchName searchName) {

        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("discountedBooks", bookService.getDiscount());
        model.addAttribute("booksBestDiscount", bookService.getBooks());
        model.addAttribute("amountInCart", booksInCart.amountOfItemsInCart());

        return "index";
    }

    /**
     * This function return the page of the admin page.
     * @param book - an object for the html.
     * @param principal - an allowed principle. Someone who didn't fill the information can't reach here/
     * @param model - the model that allows us to add attribute.
     * @param payments - the payments object for the html.
     * @return - the admin/index page.
     */
    @RequestMapping("/admin")
    public String adminIndex (Book book,Principal principal,Model model,PaymentTracker payments)  {
        /**
         * A function that handles the page of the admin.
         * Displays the order table and the book table in the order.
         */
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("payments",paymentTrackerService.getPayments());
        return "admin/index";
    }

    /**
     * A function that handles the admin connection.
     * @param book - a book object for the html.
     * @param model - an object that allows us to add attributes.
     * @return - the admin/index page.
     */
    @GetMapping("/signup")
    public String showSignUpForm(Book book, Model model) {
        return "admin/index";
    }

    /**
     * A function that handles the page for adding books to the list
     * @param book - a book object for the html.
     * @param result - a result object for the html.
     * @param model - the model that allows us to add attribute.
     * @return -the admin/index page.
     */
    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result,Model model) {
        try {
            bookService.saveBook(book);
            model.addAttribute("books", bookService.getBooks());

        } catch (Exception e) {
            model.addAttribute("message", "Sorry we could not add a book!");
        }
        model.addAttribute("payments",paymentTrackerService.getPayments());
        model.addAttribute("books", bookService.getBooks());
        return "admin/index";
    }

    /**
     * A function that edits a certain book in the list.
     * @param id - an id of the wanted to edit book.
     * @param model - the model that allows us to add attribute.
     * @return - admin/update-book page.
     */
    @PostMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.getBook(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("book", book);
        return "admin/update-book";
    }

    /**
     * A function that u[dates book in the list.
     * @param id - the id of the book we want to update.
     * @param book - book object for the html.
     * @param result - a result for the html.
     * @param model - the model that allows us to add attribute.
     * @return - admin/index page if succeeded or admin/update-book if some info was wrong.
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, BindingResult result, Model model) {
        /**
         * A function that update book in list of order book.
         */
        if (result.hasErrors()) {
            book.setId(id);
            return "admin/update-book";
        }

        bookService.saveBook(book);
        model.addAttribute("books", bookService.getBooks());
        return "admin/index";
    }

    /**
     * A delete function of a book in cart.
     * @param id - an id of the book.
     * @param model - the model that allows us to add attribute.
     * @return - the admin/index page.
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService
                .getBook(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid user Id:" + id)
                );
        bookService.deleteBook(book);
        model.addAttribute("books", bookService.getBooks());
        return "admin/index";
    }

    /**
     * A function that allows us to search books.
     * @param book - a book object for the html.
     * @param model - a model that allows us to add attributes.
     * @param searchName - an object that allows us to search books.
     * @param result - a result for the html.
     * @return - index page with the results of the books that were searched.
     */
    @PostMapping("/search")
    public String searchByName( Book book,
                               Model model,@ModelAttribute("SearchName") SearchName searchName,BindingResult result) {
        if(searchName.getSearchName().length()!=0)
            model.addAttribute("discountedBooks", bookService.getBookByName(searchName.getSearchName()));
        else
            model.addAttribute("discountedBooks", bookService.getBooks());

        model.addAttribute("amountInCart", booksInCart.amountOfItemsInCart());
        return "index";
    }

    /**
     * A function that creates a new book.
     * @param book - a book object for the html.
     * @param model - the model that allows us to add attribute.
     * @return - admin/add-book page.
     */
    @GetMapping("/createBook")
    public String createNewBook( Book book,Model model){
        return "admin/add-book";
    }

    /**
     * A login page that asks the user for password and username.
     * @param principal - the person who tries to log as an admin.
     * @return - if succeeded an admin/index page.
     */
    @GetMapping("/login")
    public String logoutAdmin(Principal principal){
        return "admin/index";
    }

    /**
     * Afunction that adds a book to the cart.
     * @param id - the id of the added book.
     * @param book - a book object for the html.
     * @param model - the model that allows us to add attribute.
     * @param searchName - a search object for searching for books.
     * @param result - a result object for the html.
     * @return - index page.
     */
    @GetMapping ("/persistToCart/{id}")
    public String persistToCart(@PathVariable Long id, Book book, Model model,@ModelAttribute("SearchName") SearchName searchName,
                                BindingResult result) {

        Book b=bookService.getBook(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid user Id:" + id)
                );
        booksInCart.add(b);

        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("discountedBooks", bookService.getBooks());
        model.addAttribute("booksInCart", booksInCart.getBooksInCart());
        model.addAttribute("amountInCart", booksInCart.amountOfItemsInCart());

        return "index";
    }

    /**
     * A shopping cart page function that displays the user's cart.
     * @param model - the model that allows us to add attribute.
     * @return - shopping-cart page.
     */
    @GetMapping ("/shopping-cart")
    public String shoppingCart( Model model) {
        model.addAttribute("booksInCart", booksInCart.getBooksInCart());

        return "shopping-cart";
    }

    /**
     * A function that empties the cart.
     * @param model - the model that allows us to add attribute.
     * @return - shopping-cart page.
     */
    @GetMapping("/emptyCart")
    public String emptyUsersCart(Model model) {
        booksInCart.emptyCart();
        model.addAttribute("booksInCart", booksInCart.getBooksInCart());
        return "shopping-cart";
    }

    /**
     * A function that deletes a book from the cart.
     * @param id -the wanted to delete book.
     * @param model - the model that allows us to add attribute.
     * @return - shopping-cart page.
     */
    @GetMapping ("/delete-from-cart/{id}")
    public String deleteFromCart( @PathVariable("id") Long id, Model model) {

        Book book = booksInCart.getBook(id);
        booksInCart.deleteFromCart(book.getId());
        model.addAttribute("booksInCart", booksInCart.getBooksInCart());

        return "shopping-cart";
    }

    /**
     * The payment function that allows the user to pay.
     * @param model - the model that allows us to add attribute.
     * @param searchName - an object that allows the user to search books in the shop.
     * @return - if succeeded a payment page, or no-books page.
     */
    @GetMapping("/payment")
    public String payForBooks(Model model, @ModelAttribute("SearchName") SearchName searchName) {
        ArrayList<String> nameBook = new ArrayList<>();
        double totalPrice = 0.0;

        for(Book book : booksInCart.getBooksInCart()){
            Book b=bookService.getBook(book.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Invalid user Id:" + book.getId()));

            if(b.getQuantity()-(booksInCart.getBook(b.getId()).getQuantity())<0)//not enough books in stock
                nameBook.add(booksInCart.getBook(b.getId()).getName());
        }

        if(nameBook.size() > 0) {
            model.addAttribute("unavailableBooks", nameBook);
            return "no-books";
        }

        for(Book book : booksInCart.getBooksInCart()){
            Book b=bookService.getBook(book.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Invalid user Id:" + book.getId()));

            totalPrice += (book.getPrice() - (book.getPrice() * (book.getDiscount()/100)))*book.getQuantity();
            b.setQuantity(b.getQuantity()-book.getQuantity());
        }
        LocalDateTime time = LocalDateTime.now();
        PaymentTracker result=new PaymentTracker(totalPrice,time);
        paymentTrackerService.savePayment(result);

        ArrayList<Book>orderBooks=new ArrayList<>(booksInCart.getBooksInCart());
        model.addAttribute("books", bookService.getBooks());
        model.addAttribute("discountedBooks", bookService.getBooks());
        model.addAttribute("booksInCart", orderBooks);
        model.addAttribute("amountInCart", booksInCart.amountOfItemsInCart());
        model.addAttribute("total", totalPrice);
        model.addAttribute("payments", paymentTrackerService.getPayments());
        booksInCart.emptyCart();


        return "payment";
    }

}



