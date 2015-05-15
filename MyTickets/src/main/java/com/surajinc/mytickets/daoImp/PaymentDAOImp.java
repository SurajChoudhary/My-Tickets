package com.surajinc.mytickets.daoImp;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surajinc.mytickets.dao.PaymentDAO;
import com.surajinc.mytickets.pojo.Payment;

@Repository
public class PaymentDAOImp implements PaymentDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(payment);
	}

	@Override
	public Payment lastPayment() {
		// TODO Auto-generated method stub
		
		Query q= sessionFactory.getCurrentSession().createQuery("from Payment order by PAYMENT_ID DESC");
		q.setMaxResults(1);
		return (Payment) q.uniqueResult();
	}
}
