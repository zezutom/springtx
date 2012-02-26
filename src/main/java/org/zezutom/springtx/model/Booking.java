package org.zezutom.springtx.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A flight booking.
 * 
 * @author tomasz
 */
@Entity
@Table(name="bookings")
public class Booking implements Serializable {

    public static final String AUDIT_INFO = "'%s' booked a flight #%d, payment made?: %s";
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)    
    private Long id;
    
    @Column(nullable=false)
    private String email;
    
    private boolean paid;
    
    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Flight flight;

    public Booking() {}
    
    public Booking(String email) {
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }        
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
        this.flight.addBooking(this);
    }

    @Override
    public String toString() {        
        return String.format(AUDIT_INFO, email, flight.getId(), paid);
    }
        
}
