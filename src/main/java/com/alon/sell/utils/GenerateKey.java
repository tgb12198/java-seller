package com.alon.sell.utils;

import java.util.Random;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/1 19:55
 * @description：生产订单号
 * @modified By：
 * @version: v1.0.0.1$
 */
public class GenerateKey {
    /**
     * 生成订单号
     * 格式：随机数+日期
     *
     * @return
     */
    public static synchronized String generateOrderKey() {
        Random random = new Random();
        Integer number = random.nextInt(99999) + 10000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    /**
     * 生成订单详情主键
     *
     * @return
     */
    public static synchronized String generateOrderDetailId() {
        Random random = new Random();
        Integer number = random.nextInt(999999) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
