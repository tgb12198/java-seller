package com.alon.sell;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggerTest {
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void Test() {
        String name = "alon";
        String password = "123456";
        logger.info("name:{},password:{}", name, password);
        logger.error("name:{},password:{}", name, password);
        logger.debug("name:{},password:{}", name, password);
    }
}
