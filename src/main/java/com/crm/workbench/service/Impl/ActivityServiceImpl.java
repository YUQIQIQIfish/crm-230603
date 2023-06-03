package com.crm.workbench.service.Impl;

import com.crm.workbench.mapper.ActivityMapper;
import com.crm.workbench.pojo.Activity;
import com.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    public int queryCountOfActivityBycondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }

    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    public int saveEditActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    public List<Activity> queryAllActivities() {
        return activityMapper.selectAllActivities();
    }

    public int saveCreateActivityByList(List<Activity> activityList) {
        return activityMapper.insertActivityByList(activityList);
    }

    public Activity quertActivityForDetailById(String id) {
        return activityMapper.selectActivityForDetailById(id);
    }
}
