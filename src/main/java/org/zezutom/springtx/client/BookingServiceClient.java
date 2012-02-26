package org.zezutom.springtx.client;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.zezutom.springtx.model.PaymentFailedException;
import org.zezutom.springtx.service.BookingService;

/**
 *
 * @author tomasz
 */
@Service("bookingServiceClient")
public class BookingServiceClient implements BookingClient {

    @Resource
    private BookingService service;
    
    @Override
    public Long bookFlight(Long id, String email, String cardNumber) throws PaymentFailedException {              
        return service.bookFlight(id, email, cardNumber);
    }
    
    
}
