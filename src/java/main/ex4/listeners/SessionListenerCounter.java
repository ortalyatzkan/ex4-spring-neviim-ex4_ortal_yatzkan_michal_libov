package main.ex4.listeners;


import main.ex4.beans.BooksInCart;
import main.ex4.beans.BooksInCart;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * a @WebListener class for session count
 * the @Component is needed only if we INJECT beans
 */
@Component
@WebListener
public class SessionListenerCounter implements HttpSessionListener {
    private final AtomicInteger activeSessions;

    // named bean injection
    @Resource(name = "applicationBeanExample")
    private BooksInCart booksInCart;

    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }

    public int getTotalActiveSession() {
        return activeSessions.get();
    }
    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("SessionListenerCounter +++ Total active session are " + activeSessions.get());

        // example of application bean accessed from session listener
        if (booksInCart!= null)
            System.out.println("application bean size = " + booksInCart.getBooksInCart().size());


    }
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("SessionListenerCounter --- Total active session are " + activeSessions.get());
    }
}
