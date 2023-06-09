package com.crm.workbench.mapper;

import com.crm.workbench.pojo.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat May 06 15:07:59 CST 2023
     */
    int deleteByPrimaryKey(String id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat May 06 15:07:59 CST 2023
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat May 06 15:07:59 CST 2023
     */
    Activity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat May 06 15:07:59 CST 2023
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Sat May 06 15:07:59 CST 2023
     */
    int updateByPrimaryKey(Activity record);

    /**
     * 保存创建的市场活动
     * @param activity
     * @return
     */
    int insertActivity(Activity activity);

    /**
     * 根据条件分页查询市场活动列表
     * @param map
     * @return
     */
    List<Activity> selectActivityByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件查询市场活动的总条数
     * @param map
     * @return
     */
    int selectCountOfActivityByCondition(Map<String,Object> map);

    /**
     * 根据ids批量删除
     * @param ids
     * @return
     */
    int deleteActivityByIds(String[] ids);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Activity selectActivityById(String id);

    /**
     * 保存修改的市场活动
     * @param activity
     * @return
     */
    int updateActivity(Activity activity);

    /**
     * 查询所有的市场活动
     * @return
     */
    List<Activity> selectAllActivities();

    /**
     * 批量保存创建的市场活动
     * @param activityList
     * @return
     */
    int insertActivityByList(List<Activity> activityList);

    /**
     *根据id查询市场活动详细信息
     * @param id
     * @return
     */
    Activity selectActivityForDetailById(String id);
}