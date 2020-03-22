package com.alon.sell.viewobject;

import lombok.Data;

/**
 * @author ：Alon
 * @date ：Created in 2020/1/31 18:28
 * @description：model
 * @modified By：
 * @version: v1.0.0.1$
 */
@Data
public class ResultModel<T> {
    /**
     * 提示码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;
}
