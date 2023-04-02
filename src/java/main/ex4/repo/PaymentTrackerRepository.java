package main.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A payment tracker repository.
 */

public interface PaymentTrackerRepository extends JpaRepository<PaymentTracker, Long> {
    List<PaymentTracker> findById(long id);
}
