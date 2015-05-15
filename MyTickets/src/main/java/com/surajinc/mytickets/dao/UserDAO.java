package com.surajinc.mytickets.dao;

import java.util.List;

import com.surajinc.mytickets.pojo.User;

public interface UserDAO {

	public void addOrUpdateUser(User user);
	public User checkUser(String userId);
	public void removeUser(String userId);
	public User getUser(String userId);
	public List<User> listUser();
}
