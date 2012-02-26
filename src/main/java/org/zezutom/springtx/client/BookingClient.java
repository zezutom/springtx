package org.zezutom.springtx.client;

import org.zezutom.springtx.model.PaymentFailedException;

/**
 * Represents a client code making use of the booking API.
 * 
 * @author tomasz
 */
public interface BookingClient {
   
    /***
     * Books a flight.
     * 
     * @param id            flight id
     * @param email         client email
     * @param cardNumber    credit card number
     * @throws PaymentFailedException
     * @return booking id
     */
    Long bookFlight(Long id, String email, String cardNumber) throws PaymentFailedException;
}
