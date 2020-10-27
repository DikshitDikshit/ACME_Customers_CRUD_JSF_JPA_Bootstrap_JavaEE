/*****************************************************************c******************o*******v******id********
 * File: CustomerPojo.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.customers2.model;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Description: model for the Customer object.
 */
@EntityListeners(CustomerPojoListener.class)
@Entity(name = "Customer")
@Table(name = "customer")
@Access(AccessType.PROPERTY)
@NamedQueries({ @NamedQuery(name = ALL_CUSTOMERS_QUERY_NAME, query = "select c from Customer as c") })
public class CustomerPojo implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ALL_CUSTOMERS_QUERY_NAME. */
	public static final String ALL_CUSTOMERS_QUERY_NAME = "allCustomers";

	/** The id. */
	@Column(name = "ID")
	protected int id;
	
	/** The first name. */
	@Column(name = "FNAME")
	protected String firstName;
	
	/** The last name. */
	@Column(name = "LNAME")
	protected String lastName;
	
	/** The email. */
	@Column(name = "EMAIL")
	protected String email;
	
	/** The phone number. */
	@Column(name = "PHONENUMBER")
	protected String phoneNumber;
	
	/** The version. */
	@Column(name = "VERSION")
	private int version;
	
	/** The created. */
	@Column(name = "CREATED")
	private LocalDateTime created;
	
	/** The updated. */
	@Column(name = "UPDATED")
	private LocalDateTime updated;
	
	/** The editable. */
	@Transient
	private boolean editable;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id new value for id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the value for firstName
	 */
	@Column(name = "FNAME")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName new value for firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the value for lastName
	 */
	@Column(name = "LNAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName new value for lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the value for email
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email new value for email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	@Column(name = "PHONENUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	// these methods now used in Assignment 2
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the created to set
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * Gets the updated.
	 *
	 * @return the updated
	 */
	public LocalDateTime getUpdated() {
		return updated;
	}

	/**
	 * Sets the updated.
	 *
	 * @param updated the updated to set
	 */
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	/**
	 * Checks if is editable.
	 *
	 * @return the editable
	 */
	@Transient
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Sets the editable.
	 *
	 * @param editable the editable to set
	 */
	@Transient
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CustomerPojo)) {
			return false;
		}
		CustomerPojo other = (CustomerPojo) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [id=").append(id).append(", ");
		if (firstName != null) {
			builder.append("firstName=").append(firstName).append(", ");
		}
		if (lastName != null) {
			builder.append("lastName=").append(lastName).append(", ");
		}
		if (phoneNumber != null) {
			builder.append("phoneNumber=").append(phoneNumber).append(", ");
		}
		if (email != null) {
			builder.append("email=").append(email).append(", ");
		}
		builder.append("]");
		return builder.toString();
	}

}