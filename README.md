# learn-spring-boot
记录学习 spring-boot 的过程

## 技术文章

### 入坑SpringBoot
spring-boot hello world 

[入坑SpringBoot](https://dddreams.github.io/180710-入坑SpringBoot.html)

### spring-boot 集成 quartz 定时任务

**若需要集群，需添加 quartz 提供的数据表并添加配置文件 quartz.yml**
- qrtz_blob_triggers : 以Blob 类型存储的触发器。
- qrtz_calendars：存放日历信息， quartz可配置一个日历来指定一个时间范围。
- qrtz_cron_triggers：存放cron类型的触发器。
- qrtz_fired_triggers：存放已触发的触发器。
- qrtz_job_details：存放一个jobDetail信息。
- qrtz_job_listeners：job监听器。
- qrtz_locks： 存储程序的悲观锁的信息(假如使用了悲观锁)。
- qrtz_paused_trigger_graps：存放暂停掉的触发器。
- qrtz_scheduler_state：调度器状态。
- qrtz_simple_triggers：简单触发器的信息。
- qrtz_trigger_listeners：触发器监听器。
- qrtz_triggers：触发器的基本信息。

**Quartz的触发时间的配置的三种方式：**
- cron 方式：采用cronExpression表达式配置时间。
- simple 方式：和JavaTimer差不多，可以指定一个开始时间和结束时间外加一个循环时间。
- calendars 方式：可以和cron配合使用，用cron表达式指定一个触发时间规律，用calendar指定一个范围。

**注意：cron方式需要用到的4张数据表： qrtz_triggers，qrtz_cron_triggers，qrtz_fired_triggers，qrtz_job_details**

**使用 quartz 遇到的问题**
- 在定时任务执行中 service @Autowired 注解不进来
  创建 JobFactory 的Bean，并在 SchedulerConfig 中添加到 SchedulerFactoryBean
  ```
// JobFactory
@Component
public class JobFactory extends SpringBeanJobFactory implements ApplicationContextAware {
    @Autowired
    private transient AutowireCapableBeanFactory beanFactory;
    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }
    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}

// SchedulerConfig
@Configuration
public class SchedulerConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JobFactory jobFactory;
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.yml"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setSchedulerName("Cluster_Scheduler");
        factory.setDataSource(dataSource);
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setQuartzProperties(quartzProperties());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
  ```
- quartz 任务激活失败
    
    在Quartz中，当一个持久化的触发器会因为：
	1. 调度器被关闭；
	2. 线程池没有可用线程；
	3. 项目重启；
	4. 任务的串行执行；

	而错过激活时间，就会发生激活失败（misfire）。

	可以设置 quartz中CornTrigger使用的策略
	```
//所有的misfile任务马上执行
public static final int MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY = -1;
//在Trigger中默认选择MISFIRE_INSTRUCTION_FIRE_ONCE_NOW 策略
public static final int MISFIRE_INSTRUCTION_SMART_POLICY = 0;
// CornTrigger默认策略，合并部分misfire，正常执行下一个周期的任务。
public static final int MISFIRE_INSTRUCTION_FIRE_ONCE_NOW = 1;
//所有的misFire都不管，执行下一个周期的任务。
public static final int MISFIRE_INSTRUCTION_DO_NOTHING = 2;
	```
	1、 通过setMisfireInstruction方法设置misfire策略。
	```
CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
triggerFactoryBean.setName("corn_" + clazzName);
triggerFactoryBean.setJobDetail(jobFactory.getObject());
triggerFactoryBean.setCronExpression(quartzCorn);
triggerFactoryBean.setGroup(QUARTZ_TRIGGER_GROUP);
//设置misfire策略
triggerFactoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY);
triggerFactoryBean.afterPropertiesSet();
	```

	2、 也可以通过CronScheduleBuilder设置misfire策略。
	```
CronScheduleBuilder csb = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
//MISFIRE_INSTRUCTION_DO_NOTHING 
csb.withMisfireHandlingInstructionDoNothing();
//MISFIRE_INSTRUCTION_FIRE_ONCE_NOW
csb.withMisfireHandlingInstructionFireAndProceed();//(默认)
//MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
csb.withMisfireHandlingInstructionIgnoreMisfires();
	```


 