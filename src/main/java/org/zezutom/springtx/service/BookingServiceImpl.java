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
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=PaymentFailedException.class)
public class BookingServiceImpl implements BookingService {
    
    @Resource
    private BookingDAO dao;

    @Resource
    private PaymentService paymentService;
    
    @Override
    public Long bookFlight(Long id, String email, String cardNumber) throws PaymentFailedException {
        Booking booking = new Booking(email);
        booking.setFlight(dao.findFlight(id));
        Long bookingId = dao.save(booking);
        dao.addAuditLog(booking);
        paymentService.processPayment(bookingId, cardNumber);
        return bookingId;              
    }
}
