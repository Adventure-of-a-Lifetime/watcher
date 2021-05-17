package com.bosssoft.watcher.module.quartz.job;

import com.bosssoft.watcher.entity.PO.ErrorDataPO;
import com.bosssoft.watcher.module.observe.observed.ProvinceRuleObserved;
import com.bosssoft.watcher.service.RuleAdministrationService;
import com.bosssoft.watcher.utils.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

public class CheckDataErrorJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(CheckDataErrorJob.class);

    @Resource
    private RuleAdministrationService ruleAdministrationService;

    @Resource
    private ProvinceRuleObserved observed;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("开始检查脏数据" + DateUtils.retrieveDate());

        Optional<List<ErrorDataPO>> errorDataPOList = Optional.ofNullable(ruleAdministrationService.retrieveErrorDate());
        errorDataPOList.ifPresent(element -> logger.error(element.toString()));
    }
}
