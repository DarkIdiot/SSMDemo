package com.demo.model;

import java.util.List;
import java.util.Map;

/** 
 *
 * @author DarkIdiot-PC
 * @date 2016年3月8日 下午11:48:54
 */
public class UserVo {
	public List<User> users ;

	public Map<String, User> userMap;
	
	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	} 
}
