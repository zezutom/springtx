package org.zezutom.springtx.service;

import java.util.List;
import org.zezutom.springtx.model.AuditLog;
import org.zezutom.springtx.model.Booking;
import org.zezutom.springtx.model.Flight;

/**
 * Provides elementary booking manipulation services.
 * 
 * @author tomasz
 */
public interface BookingDAO {
      
    /**
     * Looks up a booking by its id.
     * 
     * @param id    booking id
     * @return  the found booking or null if nothing could be found
     */
    Booking find(Long id);
    
    /**
     * Looks up a flight by its id.
     * 
     * @param id    flight id
     * @return  the found flight or null if nothing could be found
     */    
    Flight findFlight(Long id);
    
    /**
     * Persists the booking.
     * 
     * @param booking   the booking to be persisted
     * @return the booking id
     */
    Long save(Booking booking);    
    
    /**
     * Logs key information for audit purposes.
     * 
     * @param booking 
     */
    void addAuditLog(Booking booking);
    
    /**
     * Lists an up-to-date audit log, latest entries first.
     * 
     * @return the found audit log or an empty list if nothing was found
     */
    List<AuditLog> findAuditLog();
}
