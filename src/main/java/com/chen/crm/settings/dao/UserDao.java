package com.chen.crm.settings.dao;

import java.util.List;
import java.util.Map;

import com.chen.crm.settings.domain.User;

public interface UserDao {

	User login(Map<String, String> map);

	List<User> getUserList();
	
}
