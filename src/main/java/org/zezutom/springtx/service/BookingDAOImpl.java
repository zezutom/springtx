package org.zezutom.springtx.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zezutom.springtx.model.AuditLog;
import org.zezutom.springtx.model.Booking;
import org.zezutom.springtx.model.Flight;

/**
 *
 * @author tomasz
 */
@Repository
@Transactional(propagation= Propagation.MANDATORY)
public class BookingDAOImpl implements BookingDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    public Booking find(Long id) {
        return em.find(Booking.class, id);
    }

    @Override
    @Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
    public Flight findFlight(Long id) {
        return (Flight) em.createQuery("select distinct f from Flight f left join fetch f.bookings where f.id = :id").setParameter("id", id).getSingleResult();
    }
    
    @Override
    public Long save(Booking booking) {
                
        if (booking.getId() != null) {
            booking = em.merge(booking);
        } else {
            em.persist(booking);
        }     
        return booking.getId();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void addAuditLog(Booking booking) {
        AuditLog auditLog = new AuditLog();
        auditLog.setDescription(booking.toString());
        em.persist(auditLog);
    }

    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED, readOnly=true)
    public List<AuditLog> findAuditLog() {
        return em.createQuery("select a from AuditLog a order by a.created desc").getResultList();
    }    
}
