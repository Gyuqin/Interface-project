package com.gyq.dao;

import com.gyq.pojo.User;

public class UserService {
	
	//模拟访问数据库返回数据
	public User getUserByName(String name) {
		User user  = new User();
		user.setName(name);
		user.setAge(18);
		user.setHight(150);
		user.setSex("变态");
		user.setWeight(49.9);
		return user;
	}

}
