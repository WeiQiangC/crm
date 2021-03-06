 package com.chen.crm.settings.service;

import java.util.List;

import com.chen.crm.exception.LoginException;
import com.chen.crm.settings.dao.UserDao;
import com.chen.crm.settings.domain.User;

public interface UserService {
	public User login(String loginAct,String loginPwd,String ip) throws LoginException;

	public List<User> getUserList();
}
