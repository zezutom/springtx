/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zezutom.springtx.service;

import org.zezutom.springtx.model.PaymentFailedException;

/**
 *
 * @author tomasz
 */
public interface PaymentService {

    /***
     * Proceeds a booking payment.
     * 
     * @param id            booking id
     * @param cardNumber    credit card number
     * 
     * @throws PaymentFailedException 
     */
    void processPayment(Long id, String cardNumber) throws PaymentFailedException;    
}
