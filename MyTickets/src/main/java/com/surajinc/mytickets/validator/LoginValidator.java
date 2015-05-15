package com.surajinc.mytickets.validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.surajinc.mytickets.form.LoginForm;


public class LoginValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public LoginValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LoginForm user=  (LoginForm) target;
			
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errors.empty.email","Cannot be empty");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.empty.password","Cannot be empty");
		  
		 matcher = pattern.matcher(user.getEmail());
		  if(!matcher.matches()){
			  	errors.rejectValue("email", "errors.invalid.email", "Enter Valid Email");  
		  }
		  
	}

}
