/*****************************************************************c******************o*******v******id********
 * File: NewCustomerView.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Implementation of the customer object add new operation.
 * 
 * @author Dikshit Dikshit
 *
 */
@Named(value = "newCustomer")
@RequestScoped
public class NewCustomerView implements Serializable {
	
	/**  explicit set serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The first name. */
	protected String firstName;
	
	/** The last name. */
	protected String lastName;
	
	/** The email. */
	protected String email;
	
	/** The phone number. */
	protected String phoneNumber;
	
	/** The employee controller. */
	@Inject
	@ManagedProperty("#{customerController}")
	protected CustomerController employeeController;

	/**
	 * Instantiates a new new customer view.
	 */
	public NewCustomerView() {
	}

	/**
	 * Gets the first name.
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Adds the customer.
	 */
	public void addCustomer() {
		if (allNotNullOrEmpty(firstName, lastName, email, phoneNumber)) {
			CustomerPojo theNewCustomer = new CustomerPojo();
			theNewCustomer.setFirstName(getFirstName());
			theNewCustomer.setLastName(getLastName());
			theNewCustomer.setEmail(email);
			theNewCustomer.setPhoneNumber(phoneNumber);
			employeeController.addNewCustomer(theNewCustomer);

			// clean up
			employeeController.toggleAdding();
			setFirstName(null);
			setLastName(null);
			setEmail(null);
			setPhoneNumber(null);
		}
	}

	/**
	 * All not null or empty.
	 *
	 * @param values the values
	 * @return true, if successful
	 */
	static boolean allNotNullOrEmpty(final Object... values) {
		if (values == null) {
			return false;
		}
		for (final Object val : values) {
			if (val == null) {
				return false;
			}
			if (val instanceof String) {
				String str = (String) val;
				if (str.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
}