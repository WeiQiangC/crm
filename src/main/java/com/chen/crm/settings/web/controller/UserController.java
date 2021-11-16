package com.chen.crm.settings.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.chen.crm.settings.domain.User;
import com.chen.crm.settings.service.UserService;
import com.chen.crm.settings.service.impl.UserServiceImpl;
import com.chen.crm.utils.MD5Util;
import com.chen.crm.utils.PrintJson;
import com.chen.crm.utils.ServiceFactory;

@WebServlet("/settings/user/login.do")
public class UserController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if("/settings/user/login.do".equals(path)) {
				login(request, response);
		}else if(1==1) {
			
		}
	}
	
	private void login(HttpServletRequest request,HttpServletResponse response){
		String loginAct = request.getParameter("loginAct");
		String loginPwd = request.getParameter("loginPwd");
		String ip = request.getRemoteAddr();
		//进行密码加密
		loginPwd = MD5Util.getMD5(loginPwd);
		
		UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
		
		try {
			User user = us.login(loginAct,loginPwd,ip);
			request.getSession().setAttribute("user", user);
			
			//进行json拼接
			PrintJson.printJsonFlag(response, true);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", msg);
			System.out.println(msg);
			PrintJson.printJsonObj(response, map);
		}
		
	}
}
