package main.ex4.repo;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;

/**
 * A paymentTracker class.
 */
@Entity
public class PaymentTracker {

    /**
     * The id of the book.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The amount of the order.
     */
    private double amount=1;

    /**
     * The datatime of the order.
     */
    @CreationTimestamp
    private LocalDateTime datetime;

    /**
     * default constructor.
     */
    public PaymentTracker() {}

    /**
     * constructor
     * @param newAmount- the amount we will set.
     * @param newDataTime - the datatime we will set.
     */
    public PaymentTracker(Double newAmount, LocalDateTime newDataTime){
        this.amount= newAmount;
        this.datetime = newDataTime;
    }

    /**
     * This function sets the datetime.
     * @param newDatetime - the datetime we will set.
     */
    public void setDatetime(LocalDateTime newDatetime)
    {
        this.datetime = newDatetime;
    }
    /**
     * This function gets the datetime.
     * @return the datetime of order.
     */
    public LocalDateTime getDatetime(){return this.datetime;}
    /**
     * This function sets the amount.
     * @param newAmount - the new Amount we will set.
     */
    public void setAmount(double newAmount)
    {
        this.amount = newAmount;
    }
    /**
     * This function gets the amount.
     * @return the amount.
     */
    public double getAmount(){return this.amount;}
    /**
     * This function set the id.
     * @param id- the id.
     */
    public void setId(Long id)
    {
        this.id=id;
    }
    /**
     * This function gets the id.
     * @return the id.
     */
    public Long getId(){return this.id;}

    /**
     * This function is override to toString function.
     * @return string data od class.
     */

    @Override
    public String toString(){
        return "amount: " + this.amount + " datetime: " + this.datetime.toString();
    }
}
