package com.security.spring.dao;

import java.util.List;

import com.security.spring.domain.User;

public interface IUserDao {
	public User findUser(int userId);

	public User findUserByName(String userName);

	public List<User> getAllUser();

}
