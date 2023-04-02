package main.ex4.services;
import main.ex4.repo.PaymentTracker;
import main.ex4.repo.PaymentTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * A payment tracker service class.
 */
@Service
public class PaymentTrackerService {

    @Autowired
    private PaymentTrackerRepository repository;

    /**
     * A function that save the Payment in database.
     * @param payment-payment to save.
     */

    public void savePayment(PaymentTracker payment) {
        repository.save(payment);
    }

    /**
     * A function that return all payments in database.
     * @return - All payments in database.
     */
    public List<PaymentTracker> getPayments() {
        return repository.findAll();
    }
}
