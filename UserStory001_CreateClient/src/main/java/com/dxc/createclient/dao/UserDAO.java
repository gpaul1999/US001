package com.dxc.createclient.dao;


import java.util.List;

import com.dxc.createclient.entity.User;


public interface UserDAO {
	public List<User> listUser();
	public void addUser(User user);
	public User findUser(String id);
	public int countUser();
	public void updateUserInfor(User user);
	public User findUserByClientNumber(String clientNumber);
}

