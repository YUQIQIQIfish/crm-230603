package com.crm.workbench.service.Impl;

import com.crm.workbench.mapper.ActivitiesRemarkMapper;
import com.crm.workbench.pojo.ActivitiesRemark;
import com.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("activityRemarkService")
public class ActivitiesRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivitiesRemarkMapper activitiesRemarkMapper;

    public List<ActivitiesRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activitiesRemarkMapper.selectActivityRemarkForDetailById(activityId);
    }

    public int saveCreateActivityRemark(ActivitiesRemark activitiesRemark) {
        return activitiesRemarkMapper.insertActivityRemark(activitiesRemark);
    }

    public int deleteActivityRemarkById(String id) {
        return activitiesRemarkMapper.deleteActivityRemark(id);
    }
}
