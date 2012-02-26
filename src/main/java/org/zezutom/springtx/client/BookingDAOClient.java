package org.zezutom.springtx.client;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zezutom.springtx.model.Booking;
import org.zezutom.springtx.model.PaymentFailedException;
import org.zezutom.springtx.service.BookingDAO;
import org.zezutom.springtx.service.PaymentService;

/**
 *
 * @author tomasz
 */
@Service("bookingDAOClient")
public class BookingDAOClient implements BookingClient {

    private Logger logger = LoggerFactory.getLogger(BookingDAOClient.class);
    
    @Resource
    private BookingDAO dao;

    @Resource
    private PaymentService paymentService;
    
    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor=PaymentFailedException.class)
    public Long bookFlight(Long id, String email, String cardNumber) throws PaymentFailedException {       
        Booking booking = new Booking(email);
        booking.setFlight(dao.findFlight(id));
        Long bookingId = dao.save(booking);
        dao.addAuditLog(booking);
        paymentService.processPayment(bookingId, cardNumber);
        
        return bookingId;
        
    }

    
}
