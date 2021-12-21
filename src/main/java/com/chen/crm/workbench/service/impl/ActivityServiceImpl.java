package com.chen.crm.workbench.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chen.crm.settings.dao.UserDao;
import com.chen.crm.settings.domain.User;
import com.chen.crm.utils.SqlSessionUtil;
import com.chen.crm.vo.PaginationVO;
import com.chen.crm.workbench.dao.ActivityDao;
import com.chen.crm.workbench.dao.ActivityRemarkDao;
import com.chen.crm.workbench.domain.Activity;
import com.chen.crm.workbench.domain.ActivityRemark;
import com.chen.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
	private ActivityDao activityDao= SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
	private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
	private UserDao UserDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
	
	@Override
	public boolean updateRemark(ActivityRemark ar) {
		boolean flag = false;
		if(1 == activityRemarkDao.updateRemark(ar)) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean saveRemark(ActivityRemark ar) {
		boolean flag = false;
		int count = activityRemarkDao.saveRemark(ar);
		if(count == 1) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public boolean deleteRemark(String id) {
		boolean flag = false;
		int count = activityRemarkDao.deleteRemarkById(id);
		if(count == 1) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public List<ActivityRemark> getRemarkListByAid(String activityId) {
		List<ActivityRemark> arList = activityRemarkDao.getRemarkListByAid(activityId);
		return arList;
	}
	
	@Override
	public Activity detail(String id) {
		return activityDao.detail(id);
	}
	
	public boolean update(Activity a) {
		boolean flag = false;
		if(activityDao.update(a) == 1) {
			flag = true;
		}
		return flag;
	}
	
	public Map<String, Object> getUserListAndActivity(String id) {
		List<User> uList = UserDao.getUserList();
		Activity a = activityDao.getById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uList", uList);
		map.put("a", a);
		return map;
	}
	
	public boolean save(Activity a) {
		boolean flag = false;
		if(activityDao.save(a) == 1) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public PaginationVO<Activity> pageList(Map map) {
		int total = activityDao.getTotalByCondition(map);
		
		List<Activity> dataList = activityDao.getActivityListByCondition(map);
		
		PaginationVO<Activity> vo = new PaginationVO<Activity>();
		vo.setTotal(total);
		vo.setDataList(dataList);
		return vo;
	}
	
	@Override
	public boolean delete(String[] ids) {
		boolean flag = false;
		
		//查询出需要删除的备注的数量
		int count1 = activityRemarkDao.getCountByAids(ids);
		//删除备注，返回受到影响的条数(实际删除的数量)
		int count2 = activityRemarkDao.deleteByAids(ids); 
		
		if(count1==count2) {
			flag = true;
		}
		
		int count3 = activityDao.delete(ids);
		
		if(count3 != ids.length) {
			flag = false;
		}
		return flag;
	}
}
