/*****************************************************************c******************o*******v******id********
 * File: CustomerController.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Dikshit Dikshit 040946969
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.algonquincollege.cst8277.customers2.dao.CustomerDao;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Description: Responsible for collection of Customer Pojo's in XHTML Delegates
 * all C-R-U-D behaviour to DAO.
 */
@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The Constant UICONSTS_BUNDLE_EXPR. */
    public static final String UICONSTS_BUNDLE_EXPR = "#{uiconsts}";

    /** The Constant CUSTOMER_MISSING_REFRESH_BUNDLE_MSG. */
    public static final String CUSTOMER_MISSING_REFRESH_BUNDLE_MSG = "refresh";

    /** The Constant CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG. */
    public static final String CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG = "outOfDate";

    /** The faces context. */
    @Inject
    protected FacesContext facesContext;

    /** The sc. */
    @Inject
    protected ServletContext sc;

    /** The customer dao. */
    @Inject
    protected CustomerDao customerDao;

    /** The uiconsts. */
    @Inject
    @ManagedProperty(UICONSTS_BUNDLE_EXPR)
    protected ResourceBundle uiconsts;

    /** The edit customer. */
    protected CustomerPojo editCustomer;

    /** The customers. */
    protected List<CustomerPojo> customers;

    /** The adding. */
    protected boolean adding;

    /**
     * Load customers.
     */
    public void loadCustomers() {
        logMsg("loadCustomers");
        customers = customerDao.readAllCustomers();
    }

    /**
     * Gets the customers.
     *
     * @return the customers
     */
    public List<CustomerPojo> getCustomers() {
        return this.customers;
    }

    /**
     * Sets the customers.
     *
     * @param customers the new customers
     */
    public void setCustomers(List<CustomerPojo> customers) {
        this.customers = customers;
    }

    /**
     * Checks if is adding.
     *
     * @return true, if is adding
     */
    public boolean isAdding() {
        return adding;
    }

    /**
     * Sets the adding.
     *
     * @param adding the new adding
     */
    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    /**
     * Toggles the add customer mode which determines whether the addCustomer form
     * is rendered.
     */
    public void toggleAdding() {
        this.adding = !this.adding;
    }

    /**
     * Edits the customer.
     *
     * @param cust the cust
     * @return the string
     */
    public String editCustomer(CustomerPojo cust) {
        logMsg("editCustomer");
        cust.setEditable(true);
        this.editCustomer = cust;
        return null;
    }

    /**
     * Update customer.
     *
     * @return the string
     */
    public String updateCustomer() {
        if (NewCustomerView.allNotNullOrEmpty(editCustomer.getFirstName(), editCustomer.getLastName(),
                editCustomer.getEmail(), editCustomer.getPhoneNumber())) {
            logMsg("updateCustomer");
            CustomerPojo customerToBeUpdated = customerDao.readCustomerById(editCustomer.getId());
            if (customerToBeUpdated == null) {
                // someone else deleted it
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        uiconsts.getString(CUSTOMER_MISSING_REFRESH_BUNDLE_MSG), null));
            } else {
                customerToBeUpdated = customerDao.updateCustomer(editCustomer);
                if (customerToBeUpdated == null) {
                    // OptimisticLockException
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            uiconsts.getString(CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG), null));
                } else {
                    customerToBeUpdated.setEditable(false);
                    int idx = customers.indexOf(editCustomer);
                    customers.remove(idx);
                    customers.add(idx, customerToBeUpdated);
                }
            }
            editCustomer = null;
        }
        return null; // current page
    }

    /**
     * Cancel update.
     *
     * @return the string
     */
    public String cancelUpdate() {
        editCustomer.setEditable(false);
        this.refreshCustomerForm();
        return null;
    }

    /**
     * Delete customer.
     *
     * @param custId the cust id
     */
    public void deleteCustomer(int custId) {
        logMsg("deleteCustomer: " + custId);
        CustomerPojo customerToBeRemoved = customerDao.readCustomerById(custId);
        if (customerToBeRemoved != null) {
            customerDao.deleteCustomerById(custId);
            customers.remove(customerToBeRemoved);
        }
    }

    /**
     * Adds the new customer.
     *
     * @param theNewCustomer the the new customer
     */
    public void addNewCustomer(CustomerPojo theNewCustomer) {
        logMsg("adding new Customer");
        CustomerPojo newCust = customerDao.createCustomer(theNewCustomer);
        customers.add(newCust);
    }

    /**
     * Refresh customer form.
     *
     * @return the string
     */
    public String refreshCustomerForm() {
        editCustomer = null;
        logMsg("refreshCustomerForm");
        Iterator<FacesMessage> facesMessageIterator = facesContext.getMessages();
        while (facesMessageIterator.hasNext()) {
            facesMessageIterator.remove();
        }
        return "index.xhtml?faces-redirect=true";
    }

    /**
     * Log msg.
     *
     * @param msg the msg
     */
    protected void logMsg(String msg) {
        sc.log(msg);
    }

    /**
     * Gets the edits the customer.
     *
     * @return the editCustomer
     */
    public CustomerPojo getEditCustomer() {
        return editCustomer;
    }

    /**
     * Sets the edits the customer.
     *
     * @param editCustomer the editCustomer to set
     */
    public void setEditCustomer(CustomerPojo editCustomer) {
        this.editCustomer = editCustomer;
    }
}