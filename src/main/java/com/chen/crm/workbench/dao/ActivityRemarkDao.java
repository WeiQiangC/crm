package com.chen.crm.workbench.dao;

import java.util.List;

import com.chen.crm.workbench.domain.ActivityRemark;

public interface ActivityRemarkDao {

	int getCountByAids(String[] ids);

	int deleteByAids(String[] ids);

	List<ActivityRemark> getRemarkListByAid(String activityId);

	int deleteRemarkById(String id);

	int saveRemark(ActivityRemark ar);

	int updateRemark(ActivityRemark ar);

}
