package com.alon.sell.utils;

import com.alon.sell.viewobject.ResultModel;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 22:46
 * @description：返回结果
 * @modified By：
 * @version: v1.0.0.1$
 */
public class ResultUtils {
    /**
     * 成功
     * @param object
     * @return
     */
    public static ResultModel success(Object object){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setMsg("成功");
        resultModel.setData(object);
        return resultModel;
    }

    /**
     * 空集
     * @return
     */
    public static ResultModel success(){
        return success(null);
    }

    /**
     * 失败
     * @param code
     * @param msg
     * @return
     */
    public static ResultModel error(Integer code,String msg){
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(code);
        resultModel.setMsg(msg);
        return resultModel;
    }
}
