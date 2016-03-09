package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DaoSupport;
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
		user.setId(4);
		int num = userMapper.insertSelective(user);
		if (num == 1) {
			return  user;
		}else{
			return null;
		}
	}

	@Override
	public List<User> showUser(Integer[] ids) {
		List<User> list = new ArrayList<User>();
		for (Integer id : ids) {
			list.add(userMapper.selectByPrimaryKey(id));
		}
		return list;
	}

	@Override
	public int addUsers(List<User> users) {
		int count = 0;
		for (User user : users) {
			int ret = userMapper.insert(user);
			if (ret == 1) {
				count++ ;
			}
		}
		return count;
	}

	@Override
	public User getUser(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
}