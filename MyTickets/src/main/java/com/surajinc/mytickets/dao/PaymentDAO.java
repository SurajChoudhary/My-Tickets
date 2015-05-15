package com.surajinc.mytickets.dao;

import com.surajinc.mytickets.pojo.Payment;

public interface PaymentDAO {

	public void addPayment(Payment payment);
	public Payment lastPayment();
}
