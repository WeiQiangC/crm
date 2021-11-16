package com.chen.crm.settings.service.impl;


import java.util.HashMap;
import java.util.Map;

import com.chen.crm.exception.LoginException;
import com.chen.crm.settings.dao.UserDao;
import com.chen.crm.settings.domain.User;
import com.chen.crm.settings.service.UserService;
import com.chen.crm.utils.DateTimeUtil;
import com.chen.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {
	private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
	
	public User login(String loginAct,String loginPwd,String ip) throws LoginException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loginAct", loginAct);
		map.put("loginPwd", loginPwd);
		User user = userDao.login(map);
		
		//Ω¯––µ«¬º≈–∂œ
		if(user == null) {
			throw new LoginException("’À∫≈√‹¬Î¥ÌŒÛ");
		}
		if(user.getExpireTime().compareTo(DateTimeUtil.getSysTime()) >0) {
			throw new LoginException("’À∫≈“— ß–ß");
		}
		if("0".equals(user.getLockState())) {
			throw new LoginException("’À∫≈“—À¯∂®");
		}
		if(!user.getAllowIps().contains(ip)) {
			throw new LoginException("ipµÿ÷∑ ‹œﬁ");
			
		}
		return user;
	}
}
