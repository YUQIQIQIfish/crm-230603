package com.crm.workbench.service;

import com.crm.workbench.pojo.ActivitiesRemark;

import java.util.List;

public interface ActivityRemarkService {
    List<ActivitiesRemark> queryActivityRemarkForDetailByActivityId(String ActivityId);

    int saveCreateActivityRemark(ActivitiesRemark activitiesRemark);

    int deleteActivityRemarkById(String id);
}
