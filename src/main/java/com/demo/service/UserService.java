package com.demo.service;

import java.util.List;

import com.demo.model.User;

public interface UserService {

	int insertUser(User user);

	boolean login(String name,String password);

	User register(User user);

	List<User> showUser(Integer[] ids);

	int addUsers(List<User> users);

	User getUser(int id);

}
