package com.bosssoft.watcher.module.quartz.job;

import com.bosssoft.watcher.entity.PO.ErrorRulePO;
import com.bosssoft.watcher.module.observe.observed.ProvinceRuleObserved;
import com.bosssoft.watcher.service.RuleAdministrationService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ProvinceRuleErrorJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceRuleErrorJob.class);

    @Resource
    private RuleAdministrationService ruleAdministrationService;

    @Resource
    private ProvinceRuleObserved observed;

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        logger.info("开始检查系统级规则" + ofPattern.format(localDateTime));
        Optional<List<ErrorRulePO>> errorRulePOList = Optional.ofNullable(ruleAdministrationService.retrieveProvinceRuleError());
        errorRulePOList.ifPresent(errorRule -> observed.insertErrorRule("error_province_rule",null,errorRule));
    }
}