package com.flights.project.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignupValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "customerFirstName", "empty-firstName", "First Name cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "customerLastName", "empty-lastName", "Last Name cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "customerEmail", "empty-email", "Email cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "customerPassword", "empty-password", "Password cannot be blank");
	}

}
