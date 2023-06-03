package com.crm.workbench.controller;

import com.crm.commons.contants.Contants;
import com.crm.commons.pojo.ReturnObject;
import com.crm.commons.utils.DateUtils;
import com.crm.commons.utils.HSSFUtils;
import com.crm.commons.utils.UUIDUtils;
import com.crm.settings.pojo.User;
import com.crm.settings.service.UserService;
import com.crm.workbench.pojo.ActivitiesRemark;
import com.crm.workbench.pojo.Activity;
import com.crm.workbench.service.ActivityRemarkService;
import com.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String activityIndex(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList",userList);

        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_KEY);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDataTime(new Date()));
        activity.setCreateBy(user.getId());
        //调用service层方法，保存创建的市场方法

        ReturnObject returnObject = new ReturnObject();

        try {

            int ret = activityService.saveCreateActivity(activity);
            if (ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCEED);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后再试....");
            }

        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试....");
        }

        return returnObject;
    }


    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    @ResponseBody
    public Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate,int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service方法
        List<Activity> activitiesList = activityService.queryActivityByConditionForPage(map);
        int count = activityService.queryCountOfActivityBycondition(map);

        Map<String,Object> retmap = new HashMap();
        retmap.put("activitiesList",activitiesList);
        retmap.put("count",count);
        return retmap;

    }

    @RequestMapping("/workbench/activity/deleteActivityIds.do")
    @ResponseBody
    public Object deleteActivityIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityService.deleteActivityByIds(id);
            if (ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCEED);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙请稍后");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙请稍后");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id){
        Activity activity = activityService.queryActivityById(id);
        return activity;
    }


    @RequestMapping("/workbench/activity/saveEditActivity.do")
    @ResponseBody
    public Object saveEditActivity(Activity activity,HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_KEY);
        //封装参数
        activity.setEditTime(DateUtils.formatDataTime(new Date()));
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try{
            //调用service保存修改活动
            int ret = activityService.saveEditActivity(activity);

            if (ret > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCEED);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙请重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙请重试");
        }
        return returnObject;
    }


    /**
     * 测试文件下载功能
     */
    @RequestMapping("/workbench/activity/fileDownload.do")
    public void filedownload(HttpServletResponse response) throws Exception{
        //1.获取响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //2.获取输出流
        OutputStream out = response.getOutputStream();

        //设置响应头信息，使浏览器收到响应信息之后，直接激活下载窗口
        response.addHeader("Content-Disposition","attachment;filename=mystudentList.xls");

        //读取文件inputStream，输出到浏览器outputStream
        InputStream is = new FileInputStream("/Users/macbookair/Desktop/学习/spring/spring-framework-main/spring-webmvc/src/test/resources/org/springframework/web/servlet/view/document/template.xls");
        byte[] buff =new byte[256];
        int len = 0;
        while((len=is.read(buff))!=-1){
            out.write(buff,0,len);
        }
        is.close();
        out.flush();
    }


    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivitis(HttpServletResponse response)throws Exception{
        List<Activity> activities = activityService.queryAllActivities();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        if (activities!=null && activities.size()>0){
            //遍历activityList
            Activity activity = null;
            for (int i =0;i<activities.size();i++){

                activity=activities.get(i);

                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
//        //根据wb生成excel文件
//        OutputStream os = new FileOutputStream("/Users/macbookair/Desktop/项目/activityList.xls");
//        wb.write(os);
//        os.close();
//        wb.close();

        //把生成的excel文件下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
//        InputStream is = new FileInputStream("/Users/macbookair/Desktop/项目/activityList.xls");
//        byte[] buff = new byte[256];
//        int len = 0;
//        while((len=is.read(buff))!=-1){
//            out.write(buff,0,len);
//        }
//        is.close();
        wb.write(out);
        wb.close();
        out.close();
    }

    @RequestMapping("/workbench/activity/fileUpload.do")
    @ResponseBody
    public Object fileUpload(String userName, MultipartFile myFile) throws Exception{
        System.out.println("usename="+userName);
        //把文件在服务器的指定位置生成一个同样的文件

        String originalFilename = myFile.getOriginalFilename();
        File file = new File("/Users/macbookair/Desktop/项目/crm/"+originalFilename);
        myFile.transferTo(file);

        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCEED);
        returnObject.setMessage("上传成功");
        return  returnObject;
    }


    @RequestMapping("/workbench/activity/importActivity.do")
    @ResponseBody
    public Object importActivity(MultipartFile activityFile,HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_KEY);
        ReturnObject returnObject = new ReturnObject();
        try{
            //把excel文件写到磁盘目录中
            //String originalFilename = activityFile.getOriginalFilename();
            //File file = new File("/Users/macbookair/Desktop/项目"+originalFilename);
            //activityFile.transferTo(file);
            //解析excel文件，获取文件数据,并封装为activityList
            //InputStream is = new FileInputStream("/Users/macbookair/Desktop/项目" + originalFilename);
            InputStream is = activityFile.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;
            List<Activity> activityList = new ArrayList<Activity>();
            for (int i =1;i<sheet.getLastRowNum();i++){
                row = sheet.getRow(i);
                activity = new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateTime(DateUtils.formatDataTime(new Date()));
                activity.setCreateBy(user.getId());
                for (int j =0;j<row.getLastCellNum();j++){
                    cell=row.getCell(j);
                    String cellValue = HSSFUtils.getCellValueForStr(cell);
                    if (j==0){
                        activity.setName(cellValue);
                    }else if (j==1){
                        activity.setStartDate(cellValue);
                    } else if (j==2){
                        activity.setEndDate(cellValue);
                    } else if (j==3) {
                        activity.setCost(cellValue);
                    } else if (j==4) {
                        activity.setDescription(cellValue);
                    }
                }
                activityList.add(activity);
            }
            int ret = activityService.saveCreateActivityByList(activityList);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCEED);
            returnObject.setMessage("成功导入"+ret+"条数据");

        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后");
        }
        return returnObject;
    }


    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
        Activity activity = activityService.quertActivityForDetailById(id);
        List<ActivitiesRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList", remarkList);
        return "workbench/activity/detail";
    }

}
