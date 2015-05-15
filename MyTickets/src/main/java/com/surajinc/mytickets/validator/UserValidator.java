package com.surajinc.mytickets.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.surajinc.mytickets.form.UserForm;
import com.surajinc.mytickets.service.UserService;

public class UserValidator implements Validator{

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public UserValidator(){
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserForm.class.equals(clazz);
		
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		  UserForm userForm =(UserForm)target;
		  
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "errors.empty.email","Cannot be empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.empty.password","Cannot be empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errors.empty.firstname","Cannot be empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errors.empty.lastname","Cannot be empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "errors.empty.dob","Cannot be empty");
		  
		  
		  matcher = pattern.matcher(userForm.getEmail());
		  if(!matcher.matches()){
		  	errors.rejectValue("email", "errors.invalid.email", "Enter Valid Email");  
		  }
	  
//		  Date next = new Date();
//	  
//	    if(next.after(userForm.getDob()) || (next.equals(userForm.getDob()))){
//	    	errors.rejectValue("dob", "errors.invalid.dob", "Enter Valid date of birth");
//	    }
	}
	
//	public void checkUser(UserForm form) throws UserExistsException{	
//		try{
//			userService.checkUser(form);
//		}catch(UserExistsException e){
//				throw new UserExistsException("User Already Exists");
//			
//		}
//	}	
}