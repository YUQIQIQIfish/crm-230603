package com.crm.commons.pojo;

public class ReturnObject {
    private String code;     //处理成功为1，失败为0
    private String message;  //提示信息
    private Object others;   //其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getOthers() {
        return others;
    }

    public void setOthers(Object others) {
        this.others = others;
    }
}
