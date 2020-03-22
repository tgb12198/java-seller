package com.alon.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：Alon
 * @date ：Created in 2020/2/5 14:19
 * @description：微信
 * @modified By：
 * @version: v1.0.0.1$
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeiXinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx43dab0d30d7e3507&secret=6a3398d90e03261e6317188a9eb4de38&code=" + code + "&grant_type=authorization_code";
        RestTemplate template = new RestTemplate();
        String Response = template.getForObject(url, String.class);
        System.out.println(Response);
    }
}
