/*****************************************************************c******************o*******v******id********
 * File: EmailValidator.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 *
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
 * Validation of email address.
 *
 * @author Dikshit Dikshit
 */
@FacesValidator("com.algonquincollege.cst8277.customers2.jsf.EmailValidator")
public class EmailValidator implements Validator<String> {

	/** The Constant PATTERN. */
	private static final String PATTERN = "^(.+)@(.+)$";
	
	/** The Constant INVALID_EMAIL_FORMAT. */
	private static final String INVALID_EMAIL_FORMAT = "Invalid Email format.";
	
	/** The Constant EMAIL_SHOULD_NOT_BE_EMPTY. */
	private static final String EMAIL_SHOULD_NOT_BE_EMPTY = "Email should not be empty";
	
	// really really (!) simple email pattern: at-least-1-letter, '@',
	/** The Constant EMAIL_PATTERN. */
	// at-least-1-letter
	private static final Pattern EMAIL_PATTERN = Pattern.compile(PATTERN);

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
			FacesMessage msg = new FacesMessage(EMAIL_SHOULD_NOT_BE_EMPTY, INVALID_EMAIL_FORMAT);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		if (!EMAIL_PATTERN.matcher(value).matches()) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, EMAIL_SHOULD_NOT_BE_EMPTY,
					INVALID_EMAIL_FORMAT));
		}
	}

}