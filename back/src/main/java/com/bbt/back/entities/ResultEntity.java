package com.bbt.back.entities;

/**
 * @Description:
 * @Author: 潘浩霖
 * @Date: 2019/2/23
 */
public class ResultEntity {
    private int errCode;
    private String msg;
    private Object data;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "errCode=" + errCode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
