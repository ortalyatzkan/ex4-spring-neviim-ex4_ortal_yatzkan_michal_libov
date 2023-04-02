package main.ex4;

import main.ex4.beans.BooksInCart;
import main.ex4.listeners.SessionListenerCounter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Bean  configuration.
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public ServletListenerRegistrationBean<SessionListenerCounter> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<SessionListenerCounter> listenerRegBean = new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new SessionListenerCounter());
        return listenerRegBean;
    }

    @Bean
    @SessionScope
    public BooksInCart sessionBeanExample () {
        return new BooksInCart();
    }

    @Bean
    @ApplicationScope
    public BooksInCart applicationBeanExample () {
        return new BooksInCart();
    }
}
