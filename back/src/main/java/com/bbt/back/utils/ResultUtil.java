package com.bbt.back.utils;

import com.bbt.back.model.ResultEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @Description:
 * @Author: 潘浩霖
 * @Date: 2019/2/23
 */
public class ResultUtil {
    public static ResultEntity resultReturner(int code, String msg, Object data){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setErrCode(code);
        resultEntity.setMsg(msg);
        resultEntity.setData(data);
        return resultEntity;
    }

    public static ResultEntity resultGoodReturner(Object data){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setErrCode(0);
        resultEntity.setMsg("ok");
        resultEntity.setData(data);
        return resultEntity;
    }

    public static ResultEntity resultGoodReturner( ){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setErrCode(0);
        resultEntity.setMsg("ok");
        resultEntity.setData(null);
        return resultEntity;
    }

    public static ResultEntity resultBadReturner(String msg){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setErrCode(1);
        resultEntity.setMsg(msg);
        resultEntity.setData(null);
        return resultEntity;
    }

    public static ResultEntity resultBadReturner(Object data){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setErrCode(1);
        resultEntity.setMsg("error");
        resultEntity.setData(data);
        return resultEntity;
    }

    public static ResultEntity resultBadReturner(String msg, Object data){
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setErrCode(1);
        resultEntity.setMsg(msg);
        resultEntity.setData(data);
        return resultEntity;
    }

    public static String object2JsonStr(Object o){
        ObjectMapper mapper = new ObjectMapper();
        String ret = null;
        try {
            ret = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            return ret;
        }
    }

    public static void setResponse(String body, HttpServletResponse response){
        if(body==null)
            return;
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        if (body != null && !body.isEmpty()) {
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                os.write(body.getBytes("utf-8"));
                response.setContentLength(body.getBytes("utf-8").length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setResponseWithObject(Object o,HttpServletResponse response){
        setResponse(object2JsonStr(o),response);
    }

    public static void logger_request(Logger logger, HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        sb.append(" url " + request.getRequestURL() + "\n")
                .append(" remoteAddr " + request.getRemoteAddr() + "\n")
                .append(" remoteHost " + request.getRemoteHost() + "\n")
                .append(" remotePort " + request.getRemotePort() + "\n")
                .append(" remoteUser " + request.getRemoteUser() + "\n")
                .append(" queryString " + request.getQueryString() + "\n");
        Map<String,String[]> map = request.getParameterMap();
        if(map!=null){
            sb.append(" parameterMap " + object2JsonStr(map) + "\n");
        }
        logger.warn(sb.toString());
    }
}
