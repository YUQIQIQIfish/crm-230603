package com.crm.settings.controller;

import com.crm.commons.contants.Contants;
import com.crm.commons.pojo.ReturnObject;
import com.crm.commons.utils.DateUtils;
import com.crm.settings.pojo.User;
import com.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @RequestMapping("/settings/qx/user/LoginController.do")
    public String LoginController(){

        return "settings/qx/user/login";
    }

    @Autowired
     private UserService userService;

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装参数
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用Service层方法查询用户
        User user = userService.queryUserByLoginActAndPwd(map);
        //根据查询结果，返回响应信息
        ReturnObject returnObject = new ReturnObject();

        if (user == null){
            //登陆失败
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("登陆失败，账户或密码错误");

        }else{//进一步判断是否合法
            String nowTime = DateUtils.formatDataTime(new Date());
            if (nowTime.compareTo(user.getExpireTime()) > 0){
                //登陆失败，账户过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，账户过期");
            }else if ("0".equals(user.getLockState())){
                //登陆失败，账户锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，账户锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //登陆失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("登陆失败，ip受限");
            }else {
                //登陆成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCEED);

                session.setAttribute(Contants.SESSION_KEY,user);

                System.out.println(isRemPwd);

                //如果要记住密码则写cookie，否者不写
                if ("true".equals(isRemPwd)){
                    Cookie act = new Cookie("loginAct", user.getLoginAct());
                    act.setMaxAge(10*24*60*60);
                    response.addCookie(act);

                    Cookie pwd = new Cookie("loginPwd", user.getLoginPwd());
                    pwd.setMaxAge(10*24*60*60);
                    response.addCookie(pwd);
                }else {
                    Cookie act = new Cookie("loginAct", "1");
                    act.setMaxAge(0);
                    response.addCookie(act);

                    Cookie pwd = new Cookie("loginPwd", "1");
                    pwd.setMaxAge(0);
                    response.addCookie(pwd);
                }
            }

        }
        return returnObject;
    }

    @RequestMapping("/setting/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空cookie
        Cookie act = new Cookie("loginAct", "1");
        act.setMaxAge(0);
        response.addCookie(act);

        Cookie pwd = new Cookie("loginPwd", "1");
        pwd.setMaxAge(0);
        response.addCookie(pwd);
        //销毁session
        session.invalidate();
        return "redirect:/";
    }

}
