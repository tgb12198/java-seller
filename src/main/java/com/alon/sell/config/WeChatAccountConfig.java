package com.alon.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/5 20:50
 * @description：微信账户配置
 * @modified By：
 * @version: v1.0.0.1$
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    private String mpAppId;

    private String mpAppSecret;
}
