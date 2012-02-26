package org.zezutom.springtx.test;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.zezutom.springtx.client.BookingClient;
import org.zezutom.springtx.model.AuditLog;
import org.zezutom.springtx.model.Booking;
import org.zezutom.springtx.model.Flight;
import org.zezutom.springtx.model.PaymentFailedException;
import org.zezutom.springtx.service.BookingDAO;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
/**
 *
 * @author tomasz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:app-context.xml")
@TransactionConfiguration(transactionManager="txManager", defaultRollback=true)
@Transactional
public abstract class BookingTestCase {
    
    @Resource
    private BookingDAO dao;
    
    protected abstract BookingClient getClient();
    
    private static int auditLogSize = 0;
    
    @Test
    public void itShouldBePossibleToBookAFlight() throws PaymentFailedException {
        Long bookingId = getClient().bookFlight(1l, "j.doe@test.com", "123456");
        assertBooking(bookingId, "j.doe@test.com");
    }
    
    @Test(expected=PaymentFailedException.class)    
    public void paymentFailureShouldRollbackTheBookingAsAWhole() throws PaymentFailedException {
        getClient().bookFlight(2l, "j.doe@test.com", "456123");
        fail("The booking for flight with id=2 should have failed!");
    }
    
    @AfterTransaction
    public void assertDataConsistency() {
        assertNoBookingsMadeToTheSecondFlight();
        assertAuditLogKeepsGrowing();
    }
        
    private void assertNoBookingsMadeToTheSecondFlight() {
        Flight flight = dao.findFlight(2l);
        assertNotNull(flight);
        assertTrue(flight.getBookings().isEmpty());            
    }
    
    private void assertAuditLogKeepsGrowing() {
        List<AuditLog> auditLog = dao.findAuditLog();
        assertNotNull(auditLog);
        assertThat(auditLog.size(), is(++auditLogSize));
    }
    
    private void assertBooking(Long id, String email) {
        assertNotNull(id);
        
        Booking booking = dao.find(id);
        assertNotNull(booking);        
        assertEquals(email, booking.getEmail());
    }   
}
