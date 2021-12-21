package com.chen.crm.workbench.dao;

import java.util.List;
import java.util.Map;

import com.chen.crm.workbench.domain.Activity;

public interface ActivityDao {

	int save(Activity a);

	List<Activity> getActivityListByCondition(Map map);

	int getTotalByCondition(Map map);

	int delete(String[] ids);

	Activity getById(String id);

	int update(Activity a);

	Activity detail(String id);
}
