package com.chen.crm.workbench.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Detail;

import org.apache.ibatis.javassist.expr.NewArray;

import com.chen.crm.settings.domain.User;
import com.chen.crm.settings.service.UserService;
import com.chen.crm.settings.service.impl.UserServiceImpl;
import com.chen.crm.utils.DateTimeUtil;
import com.chen.crm.utils.PrintJson;
import com.chen.crm.utils.ServiceFactory;
import com.chen.crm.utils.UUIDUtil;
import com.chen.crm.vo.PaginationVO;
import com.chen.crm.workbench.domain.Activity;
import com.chen.crm.workbench.domain.ActivityRemark;
import com.chen.crm.workbench.service.ActivityService;
import com.chen.crm.workbench.service.impl.ActivityServiceImpl;

@WebServlet({"/workbench/activity/updateRemark.do", "/workbench/activity/getUserList.do", "/workbench/activity/save.do", "/workbench/activity/pageList.do", "/workbench/activity/delete.do","/workbench/activity/getUserListAndActivity.do","/workbench/activity/update.do","/workbench/activity/detail.do","/workbench/activity/getRemarkListByAid.do","/workbench/activity/deleteRemark.do","/workbench/activity/saveRemark.do"})
public class ActivityController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if ("/workbench/activity/getUserList.do".equals(path)) {
			getUserList(request, response);
		}
		if ("/workbench/activity/save.do".equals(path)) {
			save(request, response);
		}
		if ("/workbench/activity/pageList.do".equals(path)) {
			pageList(request, response);
		}
		if( "/workbench/activity/delete.do".equals(path)) {
			delete(request, response);
		}
		if("/workbench/activity/getUserListAndActivity.do".equals(path)) {
			getUserListAndActivity(request,response);
		}
		if("/workbench/activity/update.do".equals(path)) {
			update(request,response);
		}
		if("/workbench/activity/detail.do".equals(path)) {
			detail(request,response);
		}
		if("/workbench/activity/getRemarkListByAid.do".equals(path)) {
			getRemarkListByAid(request,response);
		}
		if("/workbench/activity/deleteRemark.do".equals(path)){
			deleteRemark(request,response);
		}
		if("/workbench/activity/saveRemark.do".equals(path)) {
			saveRemark(request,response);
		}
		if(("/workbench/activity/updateRemark.do").equals(path)){
			updateRemark(request,response);
		}
	}

	private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String noteContent = request.getParameter("noteContent");
		String editTime = DateTimeUtil.getSysTime();
		String editBy  = ((User)request.getSession().getAttribute("user")).getName();
		String editFlag = "1";
		
		ActivityRemark ar = new ActivityRemark();

		ar.setId(id);
		ar.setNoteContent(noteContent);
		ar.setEditTime(editTime);
		ar.setEditBy(editBy);
		ar.setEditFlag(editFlag);
		
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		boolean flag = as.updateRemark(ar);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", flag);
		map.put("ar", ar);
		PrintJson.printJsonObj(response, map);
	
	}

	private void saveRemark(HttpServletRequest request, HttpServletResponse response) {
		String noteContent = request.getParameter("noteContent");
		String activityId = request.getParameter("activityId");
		String id = UUIDUtil.getUUID();
		String createTime = DateTimeUtil.getSysTime();
		String createBy  = ((User)request.getSession().getAttribute("user")).getName();
		String editFlag = "0";
		
		ActivityRemark ar = new ActivityRemark();
		ar.setId(id);
		ar.setNoteContent(noteContent);
		ar.setActivityId(activityId);
		ar.setCreateBy(createBy);
		ar.setCreateTime(createTime);
		ar.setEditFlag(editFlag);
		
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		boolean flag = as.saveRemark(ar);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", flag);
		map.put("ar", ar);
		PrintJson.printJsonObj(response, map);
	}

	private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		boolean flag = as.deleteRemark(id);
		PrintJson.printJsonFlag(response, flag);
		
	}

	private void getRemarkListByAid(HttpServletRequest request, HttpServletResponse response) {
		String activityId = request.getParameter("activityId");
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		List<ActivityRemark> arList = as.getRemarkListByAid(activityId);
		PrintJson.printJsonObj(response, arList);
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		Activity a = as.detail(id);
		request.setAttribute("a", a);
		request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String owner = request.getParameter("owner");
		String name = request.getParameter("name");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String cost = request.getParameter("cost");
		String description = request.getParameter("description");
		String editTime = DateTimeUtil.getSysTime();
		String editBy = ((User) request.getSession().getAttribute("user")).getName();
		Activity a = new Activity();
		a.setId(id);
		a.setOwner(owner);
		a.setName(name);
		a.setStartDate(startDate);
		a.setEndDate(endDate);
		a.setCost(cost);
		a.setDescription(description);
		a.setEditTime(editTime);
		a.setEditBy(editBy);
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		boolean flag = as.update(a);
		PrintJson.printJsonFlag(response, flag);
	}

	private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		Map<String,Object> map = as.getUserListAndActivity(id);
		PrintJson.printJsonObj(response, map);
	}

	private void getUserList(HttpServletRequest request, HttpServletResponse response) {
		UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
		List<User> uList = us.getUserList();
		PrintJson.printJsonObj(response, uList);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		String id = UUIDUtil.getUUID();
		String owner = request.getParameter("owner");
		String name = request.getParameter("name");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String cost = request.getParameter("cost");
		String description = request.getParameter("description");
		String createTime = DateTimeUtil.getSysTime();
		String createBy = ((User) request.getSession().getAttribute("user")).getName();
		Activity a = new Activity();
		a.setId(id);
		a.setOwner(owner);
		a.setName(name);
		a.setStartDate(startDate);
		a.setEndDate(endDate);
		a.setCost(cost);
		a.setDescription(description);
		a.setCreateTime(createTime);
		a.setCreateBy(createBy);
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		boolean flag = as.save(a);
		PrintJson.printJsonFlag(response, flag);
	}

	private void pageList(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String pageNoStr = request.getParameter("pageNo");
		String pageSizeStr = request.getParameter("pageSize");
		Integer pageNo = Integer.valueOf(pageNoStr);
		Integer pageSize = Integer.valueOf(pageSizeStr);
		int skipCount = (pageNo - 1) * pageSize;
		Map map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("owner", owner);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("skipCount", skipCount);
		map.put("pageSize", pageSize);
		
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		PaginationVO<Activity> vo = as.pageList(map);
		PrintJson.printJsonObj(response, vo);;
	}
	
	private void delete(HttpServletRequest request,HttpServletResponse response) {
		String ids[] = request.getParameterValues("id");
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		boolean flag = as.delete(ids);
		PrintJson.printJsonFlag(response, flag);
	}
}
