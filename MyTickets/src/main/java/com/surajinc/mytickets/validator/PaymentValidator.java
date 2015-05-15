package com.surajinc.mytickets.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.surajinc.mytickets.pojo.Payment;


public class PaymentValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Payment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		  Payment payment =(Payment)target;
		  
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "errors.empty.cardNumber","Cannot be empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardId", "errors.empty.cardId","Cannot be empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardExpirationDate", "errors.empty.cardExpirationDate","Cannot be empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "errors.empty.address","Cannot be empty");
		  
		  if(payment.getCardNumber()!=16){
			  errors.rejectValue("cardNumber", "errors.invalid.cardNumber");
		  }
		  if(payment.getCardId()==null || payment.getCardId()!=3){
			  errors.rejectValue("cardId", "errors.invalid.cardId");
		  }
	}
	

}
