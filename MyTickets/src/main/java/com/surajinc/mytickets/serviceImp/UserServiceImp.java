package com.surajinc.mytickets.serviceImp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surajinc.mytickets.dao.UserDAO;
import com.surajinc.mytickets.exception.AccountNotFoundException;
import com.surajinc.mytickets.exception.UserExistsException;
import com.surajinc.mytickets.form.LoginForm;
import com.surajinc.mytickets.form.UserForm;
import com.surajinc.mytickets.pojo.User;
import com.surajinc.mytickets.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void addOrUpdateUser(UserForm userForm) {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setEmail(userForm.getEmail());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setPassword(userForm.getPassword());
		user.setDob(userForm.getDob());
		user.setRole(userForm.getRole());
		userDAO.addOrUpdateUser(user);
	}
	
	@Override
	@Transactional
	public UserForm authenticateUser(LoginForm form) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		User user= userDAO.checkUser(form.getEmail());
		
		if(user==null)
			throw new AccountNotFoundException("User not found");
		else if(!user.getPassword().equals(form.getPassword()))
			throw new AccountNotFoundException("Password does not match");
		else{
			UserForm userForm= new UserForm();
			userForm.setEmail(user.getEmail());
			userForm.setFirstName(user.getFirstName());
			userForm.setLastName(user.getLastName());
			userForm.setPassword(user.getPassword());
			userForm.setDob(user.getDob());
			userForm.setRole(user.getRole());
			return userForm;
		}
	}

	@Override
	@Transactional
	public void checkUser(UserForm userForm) throws UserExistsException {
		// TODO Auto-generated method stub
		User user= userDAO.checkUser(userForm.getEmail());
		if(user!=null)
			throw new UserExistsException("User already Exists");
	}

	@Override
	@Transactional
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return userDAO.getUser(userId);
	}

	@Override
	@Transactional
	public void removeUser(String userId) {
		// TODO Auto-generated method stub
		userDAO.removeUser(userId);
	}

	@Override
	@Transactional
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return userDAO.listUser();
	}

	

}
