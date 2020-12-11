package com.shure.quartz;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/quartz")
public class TestQuartz extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(TestQuartz.class);

    @Autowired
    private QuartzService quartzService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("任务开始执行:" + formatter.format(System.currentTimeMillis()));
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String test = jobDataMap.get("test").toString();
        String jobName = jobDataMap.get("name").toString();
        logger.info(test + ":" + jobName);
    }
}
