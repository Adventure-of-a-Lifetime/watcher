package com.bosssoft.watcher.module.quartz.scheduler;

import com.bosssoft.watcher.module.quartz.job.CheckRuleErrorJob;
import com.bosssoft.watcher.module.quartz.job.ProvinceRuleErrorJob;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ScheduleJob {

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleProvinceRuleMonitorJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ProvinceRuleErrorJob.class)
                .withIdentity("ProvinceRuleMonitorJob", "ProvinceRuleMonitorJob").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("ProvinceRuleMonitorJob", "ProvinceRuleMonitorJob")
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void scheduleCheckRuleMonitorJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(CheckRuleErrorJob.class)
                .withIdentity("CheckRuleMonitorJob", "CheckRuleMonitorJob").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("5/10 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("CheckRuleMonitorJob", "CheckRuleMonitorJob")
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleProvinceRuleMonitorJob(scheduler);
        scheduleCheckRuleMonitorJob(scheduler);
    }
}

