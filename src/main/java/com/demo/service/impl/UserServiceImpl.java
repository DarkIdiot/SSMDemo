package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.dao.UserMapper;
import com.demo.model.User;
import com.demo.model.UserExample;
import com.demo.service.UserService;
 
 
 
@Service
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserMapper userMapper;
     
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

	@Override
	public boolean login(String name,String password) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(name).andPasswordEqualTo(password);
		
		if (userMapper.selectByExample(example).size() > 0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public User register(User user) {
		int num = userMapper.insertSelective(user);
		if (num == 1) {
			return  user;
		}else{
			return null;
		}
	}
}