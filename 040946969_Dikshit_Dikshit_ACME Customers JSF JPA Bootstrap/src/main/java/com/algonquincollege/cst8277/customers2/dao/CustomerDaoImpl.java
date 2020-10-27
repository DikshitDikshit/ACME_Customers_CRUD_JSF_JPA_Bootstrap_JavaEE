/*****************************************************************c******************o*******v******id********
 * File: CustomerDaoImpl.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Dikshit Dikshit 040946969
 */
package com.algonquincollege.cst8277.customers2.dao;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Description: Implements the C-R-U-D API for the database.
 */
@Named
@ApplicationScoped
public class CustomerDaoImpl implements CustomerDao, Serializable {
	
	/**  explicitly set serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CUSTOMER_PU. */
	public static final String CUSTOMER_PU = "acmeCustomers-PU";
	
	/** The em. */
	@PersistenceContext(unitName = CUSTOMER_PU)
	protected EntityManager em;

	/** The sc. */
	protected ServletContext sc;

	/**
	 * Instantiates a new customer dao impl.
	 *
	 * @param sc the sc
	 */
	@Inject
	public CustomerDaoImpl(ServletContext sc) {
		super();
		this.sc = sc;
	}

	/**
	 * Log msg.
	 *
	 * @param msg the msg
	 */
	protected void logMsg(String msg) {
		sc.log(msg);
	}

	// delegate all C-R-U-D operations to EntityManager

	/**
	 * Read all customers.
	 *
	 * @return the list
	 */
	@Override
	public List<CustomerPojo> readAllCustomers() {
		logMsg("reading all customers");
		TypedQuery<CustomerPojo> allCustomersQuery = em.createNamedQuery(ALL_CUSTOMERS_QUERY_NAME, CustomerPojo.class);
		return allCustomersQuery.getResultList();
	}

	/**
	 * Creates the customer.
	 *
	 * @param customer the customer
	 * @return the customer pojo
	 */
	@Override
	@Transactional
	public CustomerPojo createCustomer(CustomerPojo customer) {
		logMsg("creating an customer");
		try {
			em.persist(customer);
		} catch (Exception e) {
		}
		return customer;
	}

	/**
	 * Read customer by id.
	 *
	 * @param customerId the customer id
	 * @return the customer pojo
	 */
	@Override
    public CustomerPojo readCustomerById(int customerId) {
        logMsg("read a specific customer");
        return em.find(CustomerPojo.class, customerId);
    }

	/**
	 * Update customer.
	 *
	 * @param customerWithUpdates the customer with updates
	 * @return the customer pojo
	 */
	@Override
	@Transactional
	public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
		logMsg("updating a specific customer");
		CustomerPojo mergedCustomerPojo = null;
		try {
			mergedCustomerPojo = em.merge(customerWithUpdates);
		} catch (Exception e) {
		}
		return mergedCustomerPojo;
	}

	/**
	 * Delete customer by id.
	 *
	 * @param customerId the customer id
	 */
	@Override
	@Transactional
	public void deleteCustomerById(int customerId) {
		logMsg("deleting a specific customer");
		CustomerPojo customer = readCustomerById(customerId);
		if (customer != null) {
			em.refresh(customer);
			em.remove(customer);
		}
	}

}