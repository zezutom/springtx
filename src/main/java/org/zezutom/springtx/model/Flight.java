package org.zezutom.springtx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A very basic draft of a flight information.
 * 
 * @author tomasz
 */
@Entity
@Table(name="flights")
public class Flight implements Serializable {
   
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false)
    private String destination;

    @OneToMany(mappedBy="flight")
    private List<Booking> bookings;
    
    public Long getId() {
        return id;
    }    
    
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void addBooking(Booking booking) {
        if (booking == null) {
            return;
        }
        
        if (bookings == null) {
            bookings = new ArrayList<Booking>();
        }
        bookings.add(booking);
    }
    
    public List<Booking> getBookings() {
        return bookings == null ? null : Collections.unmodifiableList(bookings);
    }       
}
