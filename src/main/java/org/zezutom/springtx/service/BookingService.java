package org.zezutom.springtx.service;

import org.zezutom.springtx.model.PaymentFailedException;

/**
 * Provides flight booking services.
 * 
 * @author tomasz
 */
public interface BookingService {
       
    /**
     * Adds a booking to a flight.
     * 
     * @param id        flight id
     * @param email     the email the booking is linked to
     * @throws PaymentFailedException
     * @return a booking id
     */
    Long bookFlight(Long id, String email, String cardNumber) throws PaymentFailedException;
}
