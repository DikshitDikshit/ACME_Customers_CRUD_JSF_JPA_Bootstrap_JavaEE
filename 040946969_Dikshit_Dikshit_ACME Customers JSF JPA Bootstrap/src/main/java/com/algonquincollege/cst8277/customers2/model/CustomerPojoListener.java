/*****************************************************************c******************o*******v******id********
 * File: CustomerPojoListener.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.customers2.model;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * The listener interface for receiving customerPojo events.
 * The class that is interested in processing a customerPojo
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addCustomerPojoListener method. When
 * the customerPojo event occurs, that object's appropriate
 * method is invoked.
 *
 * @see CustomerPojo
 */
public class CustomerPojoListener {

    /**
     * Sets the created on date.
     *
     * @param cust the new created on date
     */
    @PrePersist
    public void setCreatedOnDate(CustomerPojo cust) {
        LocalDateTime now = LocalDateTime.now();
        cust.setCreated(now);
        //might as well call setUpdatedDate as well
        cust.setUpdated(now);
    }

    /**
     * Sets the updated date.
     *
     * @param cust the new updated date
     */
    @PreUpdate
    public void setUpdatedDate(CustomerPojo cust) {
        cust.setUpdated(LocalDateTime.now());
    }

}