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
		
		//���е�¼�ж�
		if(user == null) {
			throw new LoginException("�˺��������");
		}
		if(user.getExpireTime().compareTo(DateTimeUtil.getSysTime()) >0) {
			throw new LoginException("�˺���ʧЧ");
		}
		if("0".equals(user.getLockState())) {
			throw new LoginException("�˺�������");
		}
		if(!user.getAllowIps().contains(ip)) {
			throw new LoginException("ip��ַ����");
			
		}
		return user;
	}
}
