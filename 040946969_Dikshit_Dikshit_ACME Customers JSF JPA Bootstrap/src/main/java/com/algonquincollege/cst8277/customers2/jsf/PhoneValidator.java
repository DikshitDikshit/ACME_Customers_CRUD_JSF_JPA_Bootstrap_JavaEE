/*****************************************************************c******************o*******v******id********
 * File: EmailValidator.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Dikshit Dikshit 040946969
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validation of phone number.
 *
 * @author Dikshit Dikshit
 */
@FacesValidator("com.algonquincollege.cst8277.customers2.jsf.PhoneValidator")
public class PhoneValidator implements Validator<String> {
	
	/** The Constant INVALID_PHONE_NUMBER_FORMAT. */
	private static final String INVALID_PHONE_NUMBER_FORMAT = "Invalid Phone Number format.";
	
	/** The Constant PHONE_NUMBER_SHOULD_NOT_BE_EMPTY. */
	private static final String PHONE_NUMBER_SHOULD_NOT_BE_EMPTY = "Phone Number should not be empty";
	
	/** The Constant PATTERN. */
	private static final String PATTERN = "^(\\+\\d( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
	
	/** The Constant PHONE_PATTERN. */
	// North American phoneNumber pattern
	private static final Pattern PHONE_PATTERN = Pattern.compile(PATTERN);

	/**
	 * Validate.
	 *
	 * @param context the context
	 * @param component the component
	 * @param value the value
	 * @throws ValidatorException the validator exception
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		if (value == null) {
			FacesMessage msg = new FacesMessage(PHONE_NUMBER_SHOULD_NOT_BE_EMPTY, INVALID_PHONE_NUMBER_FORMAT);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		if (!PHONE_PATTERN.matcher(value).matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_PHONE_NUMBER_FORMAT,
					INVALID_PHONE_NUMBER_FORMAT));
		}
	}

}