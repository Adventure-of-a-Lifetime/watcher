package com.bosssoft.watcher.module.quartz;

import com.bosssoft.watcher.module.quartz.scheduler.ScheduleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StartJob implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartJob.class);

    @Resource
    private ScheduleJob scheduleJob;

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run(String... args) throws Exception {
        scheduleJob.scheduleJobs();
        logger.info("任务已启动:" + ofPattern.format(localDateTime));
    }
}
