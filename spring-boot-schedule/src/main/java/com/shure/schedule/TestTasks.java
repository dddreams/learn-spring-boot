package com.shure.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTasks {

    @Scheduled(cron = "0 */1 * * * ?")
    public void testTask() {
        log.info("哈哈，我执行了！");
    }
}
