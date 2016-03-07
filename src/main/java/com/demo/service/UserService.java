package com.demo.service;

import com.demo.model.User;

public interface UserService {

	int insertUser(User user);

	boolean login(String name,String password);

	User register(User user);

}
