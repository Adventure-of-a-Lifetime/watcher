package com.bosssoft.watcher.service.impl;

import com.bosssoft.watcher.dao.RuleAdministrationDAO;
import com.bosssoft.watcher.entity.PO.ErrorDataPO;
import com.bosssoft.watcher.entity.PO.ErrorRulePO;
import com.bosssoft.watcher.entity.PO.RuleSetPO;
import com.bosssoft.watcher.module.fork.CheckDataForkJoin;
import com.bosssoft.watcher.module.fork.CheckRuleForkJoin;
import com.bosssoft.watcher.service.RuleAdministrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

@Service
public class RuleAdministrationServiceImpl implements RuleAdministrationService {

    private static final Logger logger = LoggerFactory.getLogger(RuleAdministrationServiceImpl.class);

    @Resource
    private RuleAdministrationDAO ruleAdministrationDAO;

    @Override
    public List<RuleSetPO> retrieveAllRule() {
        return ruleAdministrationDAO.retrieveAllRule();
    }

    @Override
    public List<ErrorRulePO> retrieveProvinceRuleError() {
        return ruleAdministrationDAO.retrieveProvinceRuleError();
    }

    @Override
    public void insertErrorRule(List<ErrorRulePO> errorRulePOList) {
        ruleAdministrationDAO.insertErrorRule(errorRulePOList);
    }

    @Override
    public List<ErrorRulePO> retrieveErrorRule() {
        List<Map<String, Object>> info = ruleAdministrationDAO.retrieveRuleInfo();
        ForkJoinPool pool = new ForkJoinPool();
        CheckRuleForkJoin checkRuleForkJoin = new CheckRuleForkJoin(info, ruleAdministrationDAO);
        pool.submit(checkRuleForkJoin);
        List<ErrorRulePO> list = new ArrayList<>();
        try {
            list = checkRuleForkJoin.get();
        }catch (Exception e) {
            logger.error("获取错误规则异常");
        }
        return list;
    }

    @Override
    public List<String> retrieveRegionCode() {
        return ruleAdministrationDAO.retrieveRegionCode();
    }

    @Override
    public List<ErrorDataPO> retrieveErrorDate() {
        List<String> regionCode = ruleAdministrationDAO.retrieveRegionCode();
        ForkJoinPool pool = new ForkJoinPool();
        CheckDataForkJoin checkDataForkJoin = new CheckDataForkJoin(regionCode);
        pool.submit(checkDataForkJoin);
        List<ErrorDataPO> list = new ArrayList<>();

        try {
            list = checkDataForkJoin.get();
        }catch (Exception e) {
            logger.error("获取错误规则异常");
        }
        return list;
    }

}
