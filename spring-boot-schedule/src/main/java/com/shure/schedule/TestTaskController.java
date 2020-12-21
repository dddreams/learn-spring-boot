package com.shure.schedule;

import com.shure.schedule.config.DefaultSchedulingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TestTaskController {

    @Autowired
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;

    @GetMapping("/add")
    public String add(@RequestParam(name = "name") String name) {
        defaultSchedulingConfigurer.addTriggerTask(name,
                new TriggerTask(
                        () -> System.out.println("hello world!"),
                        new CronTrigger("0/5 * * * * ? ")));//5秒执行一次
        return "任务开启成功";
    }

    @GetMapping("/del")
    public String del(@RequestParam(name = "name") String name) {
        defaultSchedulingConfigurer.cancelTriggerTask(name);
        return "任务删除成功";
    }

}
