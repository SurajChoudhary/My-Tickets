package com.surajinc.mytickets.service;


import java.util.List;

import com.surajinc.mytickets.exception.AccountNotFoundException;
import com.surajinc.mytickets.exception.UserExistsException;
import com.surajinc.mytickets.form.LoginForm;
import com.surajinc.mytickets.form.UserForm;
import com.surajinc.mytickets.pojo.User;

public interface UserService {

	public void addOrUpdateUser(UserForm user);
	public UserForm authenticateUser(LoginForm form) throws AccountNotFoundException;
	public void checkUser(UserForm user) throws UserExistsException;
	public void removeUser(String userId);
	public User getUser(String userId);
	public List<User> listUser();
}
