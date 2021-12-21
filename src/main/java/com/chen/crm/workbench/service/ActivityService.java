package com.chen.crm.workbench.service;

import java.util.List;
import java.util.Map;

import com.chen.crm.vo.PaginationVO;
import com.chen.crm.workbench.domain.Activity;
import com.chen.crm.workbench.domain.ActivityRemark;

public interface ActivityService {

	boolean save(Activity a);

	PaginationVO<Activity> pageList(Map map);

	boolean delete(String[] ids);

	Map<String, Object> getUserListAndActivity(String id);

	boolean update(Activity a);

	Activity detail(String id);

	List<ActivityRemark> getRemarkListByAid(String activityId);

	boolean deleteRemark(String id);

	boolean saveRemark(ActivityRemark ar);

	boolean updateRemark(ActivityRemark ar);

}
