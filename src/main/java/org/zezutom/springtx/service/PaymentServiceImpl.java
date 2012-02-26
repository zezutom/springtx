package org.zezutom.springtx.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zezutom.springtx.model.Booking;
import org.zezutom.springtx.model.PaymentFailedException;

/**
 *
 * @author tomasz
 */
@Service
@Transactional(propagation= Propagation.MANDATORY)
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private BookingDAO dao;
    
    @Override
    public void processPayment(Long id, String cardNumber) throws PaymentFailedException {
        if (cardNumber == null || !cardNumber.startsWith("123")) {
            throw new PaymentFailedException();
        }
        Booking booking = dao.find(id);
        booking.setPaid(true);
        dao.save(booking);
    }
    
}
