package org.zezutom.springtx.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tomasz
 */
@Entity
@Table(name="audit_logs")
public class AuditLog implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date created;
    
    @Column(nullable=false)
    private String description;

    public AuditLog() {
        this.created = new Date();
    }
    
    public Long getId() {
        return id;
    }    
    
    public Date getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
