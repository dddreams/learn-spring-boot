package com.shure.quartz;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class QuartzTest {

    private static final Logger logger = LoggerFactory.getLogger(QuartzTest.class);

    @Autowired
    private QuartzService quartzService;

    @Test
    public void quartzTest() {
        logger.info("添加定时任务");

        String jobName = "test-1";
        Map<String, Object> map = new HashMap<>();
        map.put("test", "测试任务执行");
        map.put("name", jobName);

        quartzService.deleteJob(jobName, "test");

        quartzService.addJob(TestQuartz.class, jobName, "test", "0 */2 * * * ?", map);
    }

}
