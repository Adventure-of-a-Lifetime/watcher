package com.bosssoft.watcher.service;

import com.bosssoft.watcher.entity.PO.ErrorDataPO;
import com.bosssoft.watcher.entity.PO.ErrorRulePO;
import com.bosssoft.watcher.entity.PO.RuleSetPO;

import java.util.List;

public interface RuleAdministrationService {

    List<RuleSetPO> retrieveAllRule();

    List<ErrorRulePO> retrieveProvinceRuleError();

    void insertErrorRule(List<ErrorRulePO> errorRulePOList);

    List<ErrorRulePO> retrieveErrorRule();

    List<String> retrieveRegionCode();

    List<ErrorDataPO> retrieveErrorDate();
}
